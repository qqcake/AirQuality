# AirQuality
A simple project fetching air quality info from EPA Taiwan.

API: https://data.epa.gov.tw/swagger/en/#/air/get_aqx_p_432\
Apply for API key: http://data.epa.gov.tw/en/api-term

Please install you API key by adding the following line into local.property file
```
# local.properties
api_key="Your API key"
```

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
