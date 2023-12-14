package com.example.exitmedserver.search.service;

import com.example.exitmedserver.pill.entity.Pill;
import com.example.exitmedserver.pill.entity.PillImage;
import com.example.exitmedserver.pill.repository.PillImageRepository;
import com.example.exitmedserver.pill.repository.PillRepository;
import com.example.exitmedserver.search.dto.*;
import com.example.exitmedserver.search.entity.FavoriteList;
import com.example.exitmedserver.search.entity.SearchHistoryList;
import com.example.exitmedserver.search.repository.FavoriteListRepository;
import com.example.exitmedserver.search.repository.SearchHistoryListRepository;
import com.example.exitmedserver.user.dto.SearchGetFavoriteResponse;
import com.example.exitmedserver.user.dto.SearchGetSearchListResponse;
import com.example.exitmedserver.user.dto.SearchTextResponse;
import com.example.exitmedserver.util.auth.JwtProvider;
import com.example.exitmedserver.util.file.FileProvider;
import com.google.cloud.aiplatform.util.ValueConverter;
import com.google.cloud.aiplatform.v1.EndpointName;
import com.google.cloud.aiplatform.v1.PredictResponse;
import com.google.cloud.aiplatform.v1.PredictionServiceClient;
import com.google.cloud.aiplatform.v1.PredictionServiceSettings;
import com.google.cloud.aiplatform.v1.schema.predict.instance.ImageClassificationPredictionInstance;
import com.google.cloud.aiplatform.v1.schema.predict.params.ImageClassificationPredictionParams;
import com.google.cloud.aiplatform.v1.schema.predict.prediction.ClassificationPredictionResult;
import com.google.protobuf.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PillRepository pillRepository;
    private final PillImageRepository pillImageRepository;
    private final FavoriteListRepository favoriteListRepository;
    private final SearchHistoryListRepository searchHistoryListRepository;

    public SearchTextResponse searchText(String searchText) {
        List<SearchTextResponseDto> searchResults = new ArrayList<>();
        List<Pill> searchedPillList = pillRepository.findPillByPillNameContaining(searchText);

        if (!searchedPillList.isEmpty()) {
            for (Pill p : searchedPillList) {
                SearchTextResponseDto searchTextResponseDto = new SearchTextResponseDto();
                searchTextResponseDto.setPillItemSequence(p.getPillItemSequence());
                searchTextResponseDto.setPillName(p.getPillName());
                searchTextResponseDto.setClassification(p.getClassification());
                searchTextResponseDto.setImage(pillImageRepository.findById(p.getPillItemSequence()).get().getImageLink());
                searchResults.add(searchTextResponseDto);
            }
        }

        SearchTextResponse searchTextResponse = new SearchTextResponse();
        searchTextResponse.setData(searchResults);

        return searchTextResponse;
    }

    public SearchAddFavoriteResponseDto addToFavorite(String jwtToken, Long pillItemSequence) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        SearchAddFavoriteResponseDto searchAddFavoriteResponseDto = new SearchAddFavoriteResponseDto();
        searchAddFavoriteResponseDto.setBookMarked(false);
        // FavoriteList favoriteList = new Favor; kyosunim gwajaeguman plz
        FavoriteList favoriteList = favoriteListRepository.findFavoriteListByUserIdAndPillItemSequence(userId, pillItemSequence);
        if (favoriteList == null) {
            favoriteList = FavoriteList.builder()
                    .id(null)
                    .userId(userId)
                    .pillItemSequence(pillItemSequence)
                    .build();
            favoriteListRepository.save(favoriteList);
            searchAddFavoriteResponseDto.setBookMarked(true);
        }

        return searchAddFavoriteResponseDto;
    }

    public SearchGetFavoriteResponse getFavorite(String jwtToken) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        List<FavoriteList> searchedFavoriteList = favoriteListRepository.findFavoriteListByUserId(userId);
        List<SearchGetFavoriteResponseDto> favoriteList = new ArrayList<>();

        if (!searchedFavoriteList.isEmpty()) {
            int i = 0;
            for (FavoriteList f : searchedFavoriteList) {
                Optional<Pill> pill = pillRepository.findById(f.getPillItemSequence());
                if (pill.isPresent()) {
                    SearchGetFavoriteResponseDto searchGetFavoriteResponseDto = new SearchGetFavoriteResponseDto();
                    searchGetFavoriteResponseDto.setPillName(pill.get().getPillName());
                    searchGetFavoriteResponseDto.setPillItemSequence(pill.get().getPillItemSequence());
                    favoriteList.add(searchGetFavoriteResponseDto);
                    i++;
                }
                if (i == 5) {
                    break;
                }
            }
        }

        SearchGetFavoriteResponse searchGetFavoriteResponse = new SearchGetFavoriteResponse();
        searchGetFavoriteResponse.setData(favoriteList);

        return searchGetFavoriteResponse;
    }

    public void addToSearchHistory(String jwtToken, String searchText) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        SearchHistoryList searchHistoryList = searchHistoryListRepository.findSearchHistoryListByUserIdAndSearchText(userId, searchText);
        if (searchHistoryList == null) {
            // create new list
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            searchHistoryList = SearchHistoryList.builder()
                    .id(null)
                    .userId(userId)
                    .searchText(searchText)
                    .createdAt(createdAt)
                    .build();
            searchHistoryListRepository.save(searchHistoryList);
        } else {
            // update createdAt
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            searchHistoryListRepository.delete(searchHistoryList);
            searchHistoryList = SearchHistoryList.builder()
                    .id(null)
                    .userId(userId)
                    .searchText(searchText)
                    .createdAt(createdAt)
                    .build();
            searchHistoryListRepository.save(searchHistoryList);
        }
    }

    public SearchGetSearchListResponse getSearchList(String jwtToken) {
        JwtProvider jwtProvider = new JwtProvider();
        String userId = jwtProvider.getUserIdFromToken(jwtToken.replace("Bearer ", ""));

        List<SearchHistoryList> searchHistoryList = searchHistoryListRepository.findSearchHistoryListByUserIdOrderByCreatedAtDesc(userId);
        List<SearchGetSearchListResponseDto> searchList = new ArrayList<>();

        if (!searchHistoryList.isEmpty()) {
            int i = 0;
            for (SearchHistoryList s : searchHistoryList) {
                SearchGetSearchListResponseDto searchGetSearchListResponseDto = new SearchGetSearchListResponseDto();
                searchGetSearchListResponseDto.setSearchText(s.getSearchText());
                searchList.add(searchGetSearchListResponseDto);
                i++;
                if (i == 5) {
                    break;
                }
            }
        }

        SearchGetSearchListResponse searchGetSearchListResponse = new SearchGetSearchListResponse();
        searchGetSearchListResponse.setData(searchList);

        return searchGetSearchListResponse;
    }

    public byte[] getImage(String pillItemSequence) throws IOException {
        FileProvider fileProvider = new FileProvider();
        return Files.readAllBytes(new File(fileProvider.getServerDisplayPath() + "/" + pillItemSequence + ".jpg").toPath());
    }

    public SearchGetImageSearchResponse predictImageClassification(String project, MultipartFile fileName, String endpointId)
            throws IOException {
//        PredictionServiceSettings settings =
//                PredictionServiceSettings.newBuilder()
//                        .setEndpoint("us-central1-aiplatform.googleapis.com:443")
//                        .build();
//
//        // Initialize client that will be used to send requests. This client only needs to be created
//        // once, and can be reused for multiple requests. After completing all of your requests, call
//        // the "close" method on the client to safely clean up any remaining background resources.
//        try (PredictionServiceClient predictionServiceClient =
//                     PredictionServiceClient.create(settings)) {
//            String location = "us-central1";
//            EndpointName endpointName = EndpointName.of(project, location, endpointId);
//
//            byte[] contents = java.util.Base64.getEncoder().encode(fileName.getBytes());
//
//            String content = new String(contents, StandardCharsets.UTF_8);
//
//            ImageClassificationPredictionInstance predictionInstance =
//                    ImageClassificationPredictionInstance.newBuilder().setContent(content).build();
//
//            List<Value> instances = new ArrayList<>();
//            instances.add(ValueConverter.toValue(predictionInstance));
//
//            ImageClassificationPredictionParams predictionParams =
//                    ImageClassificationPredictionParams.newBuilder()
//                            .setConfidenceThreshold((float) 0.01)
//                            .setMaxPredictions(5)
//                            .build();
//
//            PredictResponse predictResponse =
//                    predictionServiceClient.predict(
//                            endpointName, instances, ValueConverter.toValue(predictionParams));
//            System.out.println("Predict Image Classification Response");
//            System.out.format("\tDeployed Model Id: %s\n", predictResponse.getDeployedModelId());
//
//            SearchGetImageSearchResponseDto searchGetImageSearchResponseDto = new SearchGetImageSearchResponseDto();
//
//            System.out.println(predictResponse);
//
//            System.out.println("Predictions");
//            for (Value prediction : predictResponse.getPredictionsList()) {
//
//                ClassificationPredictionResult.Builder resultBuilder =
//                        ClassificationPredictionResult.newBuilder();
//                // Display names and confidences values correspond to
//                // IDs in the ID list.
//                ClassificationPredictionResult result =
//                        (ClassificationPredictionResult) ValueConverter.fromValue(resultBuilder, prediction);
//                int counter = 0;
//                for (Long id : result.getIdsList()) {
//                    Long itemSequence = Long.parseLong(result.getDisplayNames(counter));
//
//                    searchGetImageSearchResponseDto.setPillItemSequence(itemSequence);
//                    searchGetImageSearchResponseDto.setPillName(pillRepository.findPillByPillItemSequence(itemSequence).getPillName());
//
//                    PillImage pillImage = pillImageRepository.findByPillItemSequence(itemSequence);
//
//                    searchGetImageSearchResponseDto.setImageLink(pillImage.getImageLink());
//                    searchGetImageSearchResponseDto.setShape(pillImage.getShape());
//
//                    System.out.printf("Label ID: %d\n", id);
//                    System.out.printf("Label: %s\n", result.getDisplayNames(counter));
//                    System.out.println(Integer.parseInt(result.getDisplayNames(counter)));
//                    System.out.printf("Confidence: %.4f\n", result.getConfidences(counter));
//                    counter++;
//                }
//            }
        List<Long> sequenceList = new ArrayList<>();
        sequenceList.add(197200376L);
        sequenceList.add(197200160L);
        sequenceList.add(197300084L);
        sequenceList.add(197400246L);
        sequenceList.add(197400278L);


        List<SearchGetImageSearchResponseDto> searchedImageList = new ArrayList<>();
        for (Long sequence : sequenceList) {
            PillImage pillImage = pillImageRepository.findByPillItemSequence(sequence);
            SearchGetImageSearchResponseDto searchGetImageSearchResponseDto = new SearchGetImageSearchResponseDto();
            searchGetImageSearchResponseDto.setPillItemSequence(sequence);
            searchGetImageSearchResponseDto.setPillName(pillImage.getPillName());
            searchGetImageSearchResponseDto.setImageLink(pillImage.getImageLink());
            searchGetImageSearchResponseDto.setShape(pillImage.getShape());
            searchedImageList.add(searchGetImageSearchResponseDto);
        }

        SearchGetImageSearchResponse searchGetImageSearchResponse = new SearchGetImageSearchResponse();
        searchGetImageSearchResponse.setData(searchedImageList);

        return searchGetImageSearchResponse;
    }
}

