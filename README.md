# README

### Body k dokončení, diskusy

* Testy na všechny enpointy a negativní scénáře
* Servisní vrstva, transactional manager
* Nepovinné body
* Vyhledávání - custom query, criteria, specification, elastic
* Security 


### Zadání

Vytvořte jednoduchou aplikaci, která bude udržovat vzkazy od různých autorů. Výsledek uložte na
vámi zvolený gitlab a zašlete nám odkaz.
Funkční požadavky:
* aplikace umožní uložit (editovat?, mazat?) vzkaz
  * ? umožnit editovat a mazat vzkaz jen autorovi
* aplikace umožní zobrazit vzkazy
  * vzkazy půjdou vypsat
* vzkazy půjdou filtrovat podle autora
  * ? v textu vzkazu půjde vyhledávat

Implementace:
vzkaz může vypadat následovně: Message(text, author)
* uložení (editace?, smazání?) vzkazu
  * metoda
    * pomoci REST API
    * ? pomoci messagingu
  * uložiště
    * databáze
    * ?  elasticsearch
    * ?? jiné

* vzkazy se budou zobrazovat pomoci REST API