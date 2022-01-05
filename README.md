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
La presente applicazione permette di ottenere previsioni di visibilità, umidità, temperatura massima e minima di una città ed esclusivamente per visibilità e umidità WeatherApp ne restituisce le relative statistiche. Quando l'applicazione verrà lanciata, inizierà a raccogliere i dati sulla visibilità di 2 città (Milano, Londra) e li salverà su un file ogni ora.

<a name="installazione"></a>
## Installazione
WeatherApp è installabile dal Prompt dei Comandi digitando:
```
git clone https://github.com/LudovicoGo/Progetto_OOP_ESAME 
```

<a name="configurazione"></a>
## Configurazione
Per accedere al nostro servizio è necessario modificare la variabile api_key in ServiceImpl.java. Si può ottenere una API key gratuitamente accedendo alla pagina di OpenWeather. Infine basterà avviare il web-server eseguendo WeatherAppApplication.java.

<a name="rotte"></a>
## Rotte

  















#### TODO:
1. Sistemare errore metodo calcolo varianza in classe statistics;
2. Creare percorsi file adeguati per salvataggio e lettura dei dati;
3. Creare metodo conversione data da formato dd/mm/yy con ora HH:MM in unix utc.
