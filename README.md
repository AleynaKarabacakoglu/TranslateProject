# TranslateProject
Yandex Api Key ile çeviri uygulaması

Öncelikle Yandex Api Key Nasıl Kullanılır? 

Aşağıdaki url yapısı kullanılmaktadır.
https://translate.yandex.net/api/v1.5/tr.json/translate?key=Yandex anahta
rınızın urlsi&text=çevirmek istediğiniz cümle&lang="çevirdiğinizdil-çevirilecekdil

Buradaki urlyi tarayıcınızda çalıştırdığınızda bir json döner.

Örneğin kendi api keyimle oluşturduğum url:
https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20200420T21365
9Z.6ffe4c582a0f4540.30079ac61936c66e67b74dde73ce33c6509972ad&text=merhaba%20nas%C4%B1ls%C4%B1n&lang=tr-en

Bu url sonucu dönen json:

{"code":200,"lang":"tr-en","text":["Hello How Are you"]}

Burada gördüğünüz gibi çevirmek istediğim mesaj urlmin içinde ve jsondan istediğim sonuç ise "text" dizisinin içinde dönmüş.

Bunu uygulamam da kullanmak için dönen sonucu parse etmeliyim. Ben bunun için volley kütüphanesini kullandım.

Dilleri ve çevirmek istediğim metni parametre olarak verdim ve böylece yandex apinin içindeki kadar dil kullanabilirim.

Örnek olarak yaptığım bir uygulama olduğu için şimdilik sadece 3 dil ekledim bunu ise spinnerın içine attım.

Ayrıca Firebase ile kullanıcı girişini ve kaydını sağladım, register kısmından kayıt olduktan sonra uygulamayı kullanabilirsiniz.

Direkt giriş yapmak isterseniz önceden oluşturduğum :
email: aleyna@gmail.com
şifre: 12345678

ile giriş yapabilirsiniz.
