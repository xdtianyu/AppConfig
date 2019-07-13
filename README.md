# AppConfig
App config is a client library for config files hosting by MinIo service.

## Usage

```
config = Config.Builder()
    .endpoint("YOUR_ENDPOINT")
    .accessKey("YOUR_ACCESS_KEY")
    .secretKey("YOUR_SECRET_KEY")
    .build()

config.get(Status::class.java, "CallerInfo.json")
```kotlin

See [MainActivity.kt](https://github.com/xdtianyu/AppConfig/blob/master/app/src/main/java/org/xdty/config/example/MainActivity.kt) for more details.