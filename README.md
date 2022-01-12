# Progetto_OOP_ESAME

<p align="center">
  La presente applicazione offre la possibilità di avere previsioni e statistiche riguardo visibilità e umidità di una data città ma anche previsioni su temperatura massima, minima e percepita. Le statistiche sarà possibile filtrarle in base ai giorni di predizione.
</p>

## INDICE:
* [Introduzione](#introduzione)
* [Installazione](#installazione)
* [Configurazione](#configurazione)
* [Rotte](#rotte)

<a name="introduzione"></a>
## Introduzione
La presente applicazione permette di ottenere previsioni di visibilità, umidità, temperatura massima e minima di una città ed esclusivamente per visibilità e umidità WeatherApp ne restituisce le relative statistiche. Quando l'applicazione verrà lanciata inizierà a raccogliere i dati sulla visibilità di 2 città (Milano, Londra) e li salverà su un file ogni ora.

<a name="installazione"></a>
## Installazione
WeatherApp è installabile dal Prompt dei Comandi digitando:
```
git clone https://github.com/LudovicoGo/Progetto_OOP_ESAME 
```

<a name="configurazione"></a>
## Configurazione
Per accedere al nostro servizio è necessario modificare l' ``` api_key ``` in [WeatherServiceImpl.java](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/service/WeatherServiceImpl.java). Si può ottenere una API key gratuitamente accedendo alla pagina di [OpenWeather](https://openweathermap.org). Infine basterà avviare il web-server eseguendo [ProgettoEsameApplication.java](https://github.com/LudovicoGo/Progetto_OOP_ESAME/blob/master/ProgettoEsame/src/main/java/com/progetto/ProgettoEsame/ProgettoEsameApplication.java).

<a name="rotte"></a>
## Rotte
Le richieste che l'utente può effettuare tramite Postman devono essere all'indirizzo
```
localhost:8080
```

Significato dei campi della risposta ottenuta da OpenWeather:
<ul>
  <li>
      <code>coord</code>
      <ul>
        <li><code>coord.lon</code> City geo location, longitude</li>
        <li><code>coord.lat</code> City geo location, latitude</li>
      </ul>
  </li>
  <li>
      <code>weather</code> (more info Weather condition codes)
      <ul>
        <li><code>weather.id</code> Weather condition id</li>
        <li><code>weather.main</code> Group of weather parameters (Rain, Snow, Extreme etc.)</li>
        <li><code>weather.description</code> Weather condition within the group. You can get the output in your language. </li>
      </ul>
  </li>
  <li>
      <code>main</code>
      <ul>
        <li><code>main.temp</code> Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. </li>
        <li><code>main.feels_like</code> Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. </li>
        <li><code>main.pressure</code> Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa</li>
        <li><code>main.humidity</code> Humidity, %</li>
        <li><code>main.temp_min</code> Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.</li>
        <li><code>main.temp_max</code> Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.</li>
      </ul>
  </li>
  <li><code>dt</code> Time of data calculation, unix, UTC
  </li>
  <li>
      <code>sys</code>
      <ul>
        <li><code>sys.id</code> Internal parameter</li>
        <li><code>sys.message</code> Internal parameter</li>
        <li><code>sys.country</code> Country code (GB, JP etc.)</li>
      </ul>
  <li><code>id</code> City ID
  </li>
  <li><code>name</code> City name
</ul>

#### TODO:
1. Sistemare errore metodo calcolo varianza in classe statistics;
2. Creare percorsi file adeguati per salvataggio e lettura dei dati;
3. Creare metodo conversione data da formato dd/mm/yy con ora HH:MM in unix utc.
