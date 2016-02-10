#Guava
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

#Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#Retrolambda
-dontwarn java.lang.invoke.*

#Leak canary
#-keep class org.eclipse.mat.** { *; }
#-keep class com.squareup.leakcanary.** { *; }
# Marshmallow removed Notification.setLatestEventInfo()
-dontwarn android.app.Notification

#Crashlytics
-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**
-keepattributes SourceFile,LineNumberTable,*Annotation*