package VTTP.NUSISS.March22DeckOfCards.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Deck {
    
    private String deck_id;
    private Integer remaining;
    private boolean isShuffled;
    private List<Card> cardsList;

    public String getDeck_id() {
        return deck_id;
    }
    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }
    public Integer getRemaining() {
        return remaining;
    }
    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
    public boolean isShuffled() {
        return isShuffled;
    }
    public void setShuffled(boolean isShuffled) {
        this.isShuffled = isShuffled;
    }
    public List<Card> getCardsList() {
        return cardsList;
    }
    public void setCardsList(List<Card> cardsList) {
        this.cardsList = cardsList;
    }

    public static Deck create(String json){
        Deck deck = new Deck();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader reader = Json.createReader(is);
            JsonObject o = reader.readObject();
            
            deck.deck_id= o.getString("deck_id");
            deck.remaining=o.getInt("remaining");

            if(o.containsKey("cards")){
                List<Card> cards = new LinkedList<>();
                JsonArray cardsArr = o.getJsonArray("cards");
                for(int i =0; i<cardsArr.size(); i++){
                    cards.add(Card.create(cardsArr.getJsonObject(i)));
                
                }
                deck.cardsList = cards;
            }

        }catch(IOException ex){
            ex.printStackTrace();
        }

        return deck;


    }

}
