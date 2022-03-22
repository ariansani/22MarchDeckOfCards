package VTTP.NUSISS.March22DeckOfCards.services;

import org.apache.catalina.connector.Response;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import VTTP.NUSISS.March22DeckOfCards.models.Deck;

@Service
public class DoCService {

    private static final String CREATE_DECK_URL = "https://deckofcardsapi.com/api/deck/new/shuffle/";

    public Deck drawCards(String deck_id, Integer count){
        String url = "https://deckofcardsapi.com/api/deck/" + deck_id + "/draw/"; 
        String drawFromDeck = UriComponentsBuilder.fromUriString(url)
        .queryParam("count", count)
        .toUriString();

        return invoke(drawFromDeck);
    }



    public Deck createDeck(int count) {
        // https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1

        String url = UriComponentsBuilder
                .fromUriString(CREATE_DECK_URL)
                .queryParam("deck_count", count)
                .toUriString();

        return invoke(url);
    }

    public Deck invoke(String url) {
        RequestEntity req = RequestEntity
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        return Deck.create(resp.getBody());
    }
}
