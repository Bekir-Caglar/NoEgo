
# Switch-Controlled BottomNavigationBar

## Proje Açıklaması

Bu proje, bir BottomNavigationBar'ı switch'lerle kontrol eden bir Android uygulamasıdır. Switch'ler, BottomNavigationBar'daki öğeleri dinamik olarak günceller.

![Uygulama Ekran Görüntüsü 1](https://github.com/user-attachments/assets/f498119d-8b39-4ea2-bd26-2b81bc460b76)
![Uygulama Ekran Görüntüsü 2](https://github.com/user-attachments/assets/ec0e48b6-2596-473d-b7d4-5a4e3b6ec27a)
![Uygulama Ekran Görüntüsü 3](https://github.com/user-attachments/assets/677512a3-0234-4bdb-b2f3-5a16b0a9e58c)

## Özellikler

- **Başlangıç Durumu:** Uygulama açıldığında 6 switch bulunur ve "Ego" switch’i açık olur.
- **Ego Switch:** Ego switch’i açık olduğunda diğer switch'ler devre dışı kalır ve BottomNavigationBar gizlenir. Ego switch’i kapalı olduğunda diğer switch'ler açılabilir ve BottomNavigationBar görünür.
- **Detay Ekranları:** BottomNavigationBar'daki öğelere tıklandığında ilgili detay ekranlarına gidilir.

## Kullanılan Teknolojiler

- **Kotlin**
- **Jetpack Navigation**
- **ViewBinding**
- **Material Components**

## Kurulum

1. **Projeyi Kopyalayın:**
   ```bash
   git clone <proje-repo-url>

