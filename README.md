
<h1>Pelikulak - RestAPI</h1>

Milaka pelikula aurkitu ditzakezu REST API honen bitartez. Genero filmografikoaren arabera sailkatuta.
Datuak MongoDB datubase baten gordetzen dire, zerbitzariak Spring teknologia erabiltzen du, Mongo-rako driverrarekin.
Aplikazioa kontainerizatuta dago eta beharrezkoa den dena exekutatzeko docker-compose bat dago definituta.

<h2>Datuak</h2>
Datuak errepositorio honetatik daude hartuta: https://github.com/prust/wikipedia-movie-data. Wikipediatek ateratakoak dira.
Aldaketa batzuk egin behar izan dizkiogu (converter.html fitxategian ikusi daiteke nola).

Datuen estruktura:
title: pelikularen titulua
year: kaleratze urtea
cast: kastingaren lista
genres: pelikularen generoak (lista moduan)
exctract: deskripzio bat
thumbnail.image: kartelaren argazkia
thumbnail.width: argazkiaren zabalera (px)
thumbnail.height: argazkiaren altuera

<h2>Rest zerbitzua</h2>
Kontsultak egitea eta datuak editatzea ahalbidetzen digu.

|  Metodoa | Path  | Erantzuna  |
| ------------ | ------------ | ------------ |
| GET  | /  | Azken 100 pelikulak.  |
| GET  | /{id}  | Pelikula bat, id-aren arabera.  |
| GET  | /genre/{genre}  | Genero jakin bat duten pelikula guztiak.  |
| POST  | /add  | Pelikula berria.  |
| PUT  | /update/{id}  | Eguneratutako pelikua itzultzen du. Id: aldatu behar den pelikularen id-a.  |
| DELETE  | /delete/{id}  | Daturik ez.  |

<h2>Docker</h2>
Dockerfile bat sortu dugu rest zerbitzua kontainerizatzeko. Hau "irudi" bat sortzeko plantilla gisa erabiltzen da. Irudia sortzerakoan kodigoa konpilatzen da eta 8080 portuan abiarazten da Spring paketea.

docker-compose erabiltzen dugu REST zerbitzua eta Mongo zerbitzua koordinatzeko. docker-compose.yaml fitxategian Mongo-ren konfigurazioa dago definituta eta lehen aipatutako rest-api kontainerizatua ere bai.
**docker compose up** exekutatzen badugu Mongo zerbitzua hasiko da lehengo eta gero rest-api-a aurrez definitutako portuetan, beraz ez dute elkar konektatzeko arazorik izango.

<h2>Mongo datubasea</h2>
Behin zerbitzuak abiarazita ditugula, MongoDBn 40spelikulak kolekzioa sortuko dugu eta errepositorio honetako data2.json-eko datuak bertara kopiatu (Nahi izanez gero).