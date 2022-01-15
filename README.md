# Progetto_OOP_ESAME

<p align="center">
  La presente applicazione offre la possibilità di avere previsioni e statistiche riguardo visibilità e umidità di una data città ma anche previsioni su temperatura massima, minima e percepita. Le statistiche sarà possibile filtrarle in base ai giorni di predizione.
</p>

## INDICE:
* [Introduzione](#introduzione)
* [Installazione](#installazione)
* [Configurazione](#configurazione)
* [Rotte](#rotte)
* [Eccezioni](#eccezioni)
* [Autori](#autori)

<a name="introduzione"></a>
## Introduzione
La nostra applicazione permette di ottenere previsioni di visibilità, umidità, temperatura massima e minima di una città ed esclusivamente per visibilità e umidità WeatherApp ne restituisce le relative statistiche. Quando l'applicazione verrà lanciata inizierà a raccogliere i dati sulla visibilità di 4 città (Milano, Valencia, Parigi e Londra) e li salverà su un file ogni ora.

<a name="installazione"></a>
## Installazione
La presente applicazione è installabile dal Prompt dei Comandi con:
```
git clone https://github.com/LudovicoGo/Progetto_OOP_ESAME 
```

<a name="configurazione"></a>
## Configurazione
Per accedere al nostro servizio è necessario modificare l' ``` api_key ``` in [WeatherServiceImpl.java](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/service/WeatherServiceImpl.java). Si può ottenere una API key gratuitamente accedendo alla pagina di [OpenWeather](https://openweathermap.org). Infine basterà avviare il web-server eseguendo [ProgettoEsameApplication.java](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/ProgettoEsameApplication.java).

<a name="rotte"></a>
## Rotte
Riferendosi al seguente indirizzo:
```
localhost:8080
```

L'utente ha a disposizione le seguenti rotte:
 
| Rotta             | Tipo rotta |        Descrizione                                           
|-------------------|------------|--------------------------------------------------------------
| /weather          |     GET    | Restituisce un JSONObject contenente le previsioni meteo istantanee (ancheumidità e visibilità) della città richiesta.
| /scheduledWeather |     GET    | Salva su file JSON le previsioni del meteo complete di una città in modo ciclico, una volta all'ora, in un certo periodo di tempo indicato.
| /visibility       |     GET    | Calcola media e varianza della visibilità, relative ad un dato periodi di tempo, di una città prendendone i valori da un file JSON.
| /humidity         |     GET    | Calcola media e varianza della umidità, relative ad un dato periodi di tempo, di una città prendendone i valori da un file JSON.

### Spiegazione rotte

  - **<b>Utilizzo rotta /weather</b>**:

    Questa rotta prende in input il nome di una città e restituisce un JSONObject con dentro le previsioni meteo istantanee complete (comprese visibilità e umidità), si può inserire una città qualsiasi e nel caso la città inserita non esista o si sbagli a scriverne il nome verrà restituito un errore. Si utilizza cosi:
  
      ```
      localhost:8080/weather?cityName={NomeCittà}
      ```
      (mettere il nome della città richiesta al posto di "NomeCittà" e rimuovere le parentesi graffe)
      
      
 - **<b>Utilizzo rotta /scheduledWeather</b>**:
 
    Questa rotta prende in input il nome di una città, un periodo di tempo, l'inizio e la fine di quel periodo del periodo di riferimento e salva su un file JSON le previsioni meteo istantanee complete (comprese visibilità e umidità), si può inserire una città qualsiasi. I periodi di tempo disponibili sono TimeSlot, Daily, ChosenDay, Weekly. Le date vanno inserite seguendo il formato ddMMyyyyHHmmss.
      I file avranno come nome "nome città + WeatherArray.json" e verranno salvati all'interno della cartella del progetto nel seguente percorso:  
      
      ```
      "src/main/resources/saved/NomeFile.json"
      ```
      
      Questa rotta si può usare in diversi modi a seconda del periodo di tempo scelto:
      
      - **<b>Daily</b>**: 
         Richiede come input necessari il nome della città e il periodo di tempo che deve essere scelto è "Daily", salva su un file le previsioni del meteo a partire da quando si invia la richiesta fino alla fine del giorno corrente, si può scrivere cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=Daily
         ```
         (mettere il nome della città richiesta al posto di "NomeCittà" e rimuovere le parentesi graffe)
         
         
      - **<b>TimeSlot</b>**: 
         Richiede come input necessari solo il nome della città, va scelto il periodo di tempo "TimeSlot", va fornita la data (e ora) del momento di inizio e della fine del salvataggio delle previsioni. Salva su un file le previsioni del meteo, ogni ora, di una <b>fascia oraria</b> definita inserendo l'ora d'inizio e quella di fine. Va usata cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=TimeSlot&initialParam={DataInizio}&finalParam={DataFine}
         ```
         (inserire i parametri e rimuovere le parentesi graffe)
         
         
      - **<b>ChosenDay</b>**: 
         Richiede come input necessari il nome della città e la data del giorno scelto (a qualsiasi ora e sempre usando il formato ddMMyyyyHHmmss), va scelto il periodo di tempo "ChosenDay". Salva su un file le previsioni del meteo di un <b>giorno a scelta diverso da quello corrente</b>, ogni ora, a partire dalle ore 00:00 del giorno indicato fino alle ore 24:00 dello stesso. Va usata cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=ChosenDay&initialParam={DataGiornoScelto}
         ```
         (inserire i parametri e rimuovere le parentesi graffe)
         
            
      - **<b>Weekly</b>**: 
         Richiede come input necessari il nome della città, va scelto il periodo di tempo "Weekly". Salva su un file le previsioni del meteo <b>della settimana</b>, ogni ora, a partire dall'ora corrente fino a 7 giorni dopo. Va usata cosi:
         
         ```
         localhost:8080/scheduledWeather?cityName={NomeCittà}&period=Weekly
         ```
         (inserire i parametri e rimuovere le parentesi graffe)
         
              
 - **<b>Utilizzo rotta /visibility</b>**:
 
    Questa rotta prende in input il nome di una città e il nome di un periodo di tempo (TimeSlot, Weekly, ChosenDay, Daily), si può inserire una città tra quelle presenti nel database di esempi (ovvero: Milan, Valencia, London, Paris).
    Chiamando questa rotta il programma prende il file su cui sono salvati i dati del meteo della città scelta nel periodo scelto, ne estrapola i valori della visibilità e li usa per calcolare media e varianza di quest'ultima.
      I file devono avere come nome "nome città + periodo + WeatherArray.json", possono essere generati tramite l'uso della rotta /scheduledWeather (vedi sopra) e sono salvati all'interno della cartella del progetto nel seguente percorso:  
      
      ```
      "src/main/resources/dataExamples/NomeFile.json"
      ```


              
 - **<b>Utilizzo rotta /humidity</b>**:
 
    Questa rotta prende in input il nome di una città e il nome di un periodo di tempo (TimeSlot, Weekly, ChosenDay, Daily), si può inserire una città tra quelle presenti nel database di esempi (ovvero: Milan, Valencia, London, Paris).
    Chiamando questa rotta il programma prende il file su cui sono salvati i dati del meteo della città scelta nel periodo scelto, ne estrapola i valori dell'umidità e li usa per calcolare media e varianza di quest'ultima.
      I file devono avere come nome "nome città + periodo + WeatherArray.json", possono essere generati tramite l'uso della rotta /scheduledWeather e sono salvati all'interno della cartella del progetto nel seguente percorso:  
      
      ```
      "src/main/resources/dataExamples/NomeFile.json"
      ```


Significato dei campi della risposta ottenuta da OpenWeather:
* ```coord``` <br /> 
     - ```coord.lon``` City geo location, longitude;
     - ```coord.lat``` City geo location, latitude;
        
* ```weather``` (more info Weather condition codes)
     - ```weather.id``` Weather condition id;
     - ```weather.main``` Group of weather parameters (Rain, Snow, Extreme etc.);
     - ```weather.description``` Weather condition within the group. You can get the output in your language.
* ```main```
     - ```main.temp``` Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     - ```main.feels_like``` Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. 
     - ```main.pressure``` Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
     - ```main.humidity``` Humidity, %
     - ```main.temp_min``` Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
     - ```main.temp_max``` Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 * ``` dt ``` Time of data calculation, unix, UTC.
 
 * ```sys```
      - ```sys.id``` Internal parameter;
      - ```sys.message``` Internal parameter;
      - ```sys.country``` Country code (GB, JP etc.);
      - ```sys.sunrise``` Sunrise time, unix, UTC;
      - ```sys.sunset``` Sunset time, unix, UTC.
 * ```id``` City ID.
 * ```name``` City name.


<a name="eccezioni"></a>
## Eccezioni
- CantFindDataException,    
- CityException,   
- TimeSlotException,

Vai su intellij, fai click destro sul file dell'eccezione che ti serve e fai "find usage" che ti mostra dove e quando sono lanciate e i messaggi che lanciano 

<a name="autori"></a>
## Autori
- Gorgoglione Ludovico 60%
- Christian Curzi 40%
  
