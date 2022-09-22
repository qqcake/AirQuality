# AirQuality
A simple project fetching air quality info from EPA Taiwan.

API: https://data.epa.gov.tw/swagger/en/#/air/get_aqx_p_432 \
Apply for API key: http://data.epa.gov.tw/en/api-term

Please install your API key by adding the following line into local.property file
```
# local.properties
api_key="Your API key"
```
## Screenshots
<img src="https://user-images.githubusercontent.com/6590878/186826320-dca5772f-cfd7-44e9-bd5e-eaa1e44a1076.jpg" width="150"> <img src="https://user-images.githubusercontent.com/6590878/186826326-d305da77-172e-436e-983a-dd534bb968b1.jpg" width="150"> <img src="https://user-images.githubusercontent.com/6590878/186826330-1e6f3011-3931-46d6-8d1a-70fc69506a09.jpg" width="150"> <img src="https://user-images.githubusercontent.com/6590878/186826336-0ba94a5d-5a5d-483e-971a-45fc7a9393f3.jpg" width="150"> <img src="https://user-images.githubusercontent.com/6590878/186826338-772caa3a-2411-46c4-ad71-0ecf5d492967.jpg" width="150">

## About
### Techniques
- Pattern - CLEAN, MVVM
- Dependency injection - Hilt
- UI - Jetpack Compose
- Navigation - Navigation
- Web API - Retrofit, Moshi
- Unit test - JUnit5

### Features
- Local cached
- Swipe refresh
- Material 3 components

### Unit tests
- AirQualityDividerTest - test if air qualities are divided into two groups by average pm2.5 values
- AirQualityRepositoryImplTest - tests repository loads from local and/or remote data source correctly

### To-dos
- More unit tests
- UI test
- Pagination
- Theming
