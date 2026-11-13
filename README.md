# SauceDemo End-to-End Web Automation Testing

Proyek otomatisasi pengujian fungsional (*Functional & End-to-End UI Testing*) pada aplikasi web e-commerce [SauceDemo](https://www.saucedemo.com/) menggunakan **Java**, **Selenium WebDriver**, dan **JUnit 5**.

## Tech Stack & Tools
* **Language:** Java
* **Automation Framework:** Selenium WebDriver
* **Test Runner & Assertions:** JUnit 5 (Jupiter)
* **IDE & Browser:** Eclipse IDE, Google Chrome (`ChromeOptions`)

## Cakupan Skenario Pengujian
| Test Case ID | File Skrip | Skenario | Validasi Utama |
| :--- | :--- | :--- | :--- |
| **TC_01** | `src/main/Scenario01.java` | Authentication & Product Detail | Validasi login `standard_user`, pengalihan ke `inventory.html`, serta verifikasi judul dan deskripsi produk *Sauce Labs Backpack*. |
| **TC_02** | `src/main/Scenario2.java` | End-to-End Checkout Flow | Alur penuh mulai dari *Add to Cart*, pengisian data pembeli, konfirmasi pesanan, hingga validasi halaman `checkout-complete.html`. |

## Dokumentasi QA (Test Artifacts)
Seluruh dokumen perencanaan dan pelaporan pengujian tersedia pada folder [`docs/`](./docs):
* **Test Plan:** Ruang lingkup pengujian, strategi otomasi, kriteria masuk/keluar, dan mitigasi risiko (*Chrome Password Manager leak detection*).
* **Test Case Specification:** Rincian langkah pengujian, *test data*, *expected result*, dan status eksekusi.

## Hasil Eksekusi (JUnit 5)
![JUnit Execution Result](docs/junit-execution-result.png)
