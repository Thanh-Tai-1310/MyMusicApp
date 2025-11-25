# MyMusicApp

MyMusicApp là ứng dụng Android viết bằng Kotlin để quản lý và phát nhạc cá nhân. Ứng dụng sử dụng ExoPlayer để phát nhạc và Firebase để đồng bộ dữ liệu (Auth / Firestore / Storage).

## Tính năng chính
- Quản lý thư viện nhạc và playlist
- Tìm kiếm cơ bản
- Phát nhạc nền (ExoPlayer) với notification
- Đồng bộ dữ liệu qua Firebase

## Công nghệ
- Kotlin
- ExoPlayer
- Firebase (Auth, Firestore, Storage)
- Android Studio, Gradle, AndroidX

## Cài đặt nhanh
1. Clone:
   git clone https://github.com/Thanh-Tai-1310/MyMusicApp.git

2. Mở project bằng Android Studio.

3. Nếu dùng Firebase: thêm `google-services.json` vào thư mục `app/`.

4. Sync Gradle và chạy trên emulator hoặc device.

## Lệnh hữu ích
- Build debug: ./gradlew assembleDebug
- Tạo AAB: ./gradlew bundleRelease

## Quyền cần thiết
- INTERNET
- FOREGROUND_SERVICE
- READ_EXTERNAL_STORAGE / READ_MEDIA_AUDIO (tùy Android version)

## Đóng góp
Fork → tạo branch → commit → PR. Vui lòng mô tả thay đổi ngắn gọn.

## Liên hệ
Tác giả: Thanh-Tai-1310 — https://github.com/Thanh-Tai-1310
