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
    implementation 'com.github.andnux:push:0.0.1'
    or
    implementation 'com.github.andnux.push:core:0.0.1'
    implementation 'com.github.andnux.push:getui:0.0.1'
    implementation 'com.github.andnux.push:meizu:0.0.1' 
    implementation 'com.github.andnux.push:mipush:0.0.1' 
}
```