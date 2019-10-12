### 快速集成：
- **Step 1.** Add the JitPack repository to your build file
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
- **Step 2.** Add the dependency
```groovy
dependencies {
    def version = "0.0.2"
    implementation "com.github.andnux:push:${version}"
    or
    implementation "com.github.andnux.push:core:${version}"
    implementation "com.github.andnux.push:getui:${version}"
    implementation "com.github.andnux.push:meizu:${version}" 
    implementation "com.github.andnux.push:mipush:${version}" 
    implementation "com.github.andnux.push:huawei:${version}" 
    implementation "com.github.andnux.push:google:${version}" 
}
```