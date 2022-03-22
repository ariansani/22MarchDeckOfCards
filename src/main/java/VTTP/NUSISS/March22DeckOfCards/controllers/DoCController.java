package VTTP.NUSISS.March22DeckOfCards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import VTTP.NUSISS.March22DeckOfCards.models.Deck;
import VTTP.NUSISS.March22DeckOfCards.services.DoCService;

@Controller
@RequestMapping("/deck")
public class DoCController {
    
    @Autowired
    private DoCService deckSvc;

    @PostMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postDeck(Model model){
        
        Deck deck = deckSvc.createDeck(1);

        model.addAttribute("deck", deck);
        model.addAttribute("cards", List.of());

        return "card_game";
    }

    @PostMapping(path="/{deck_id}",consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String drawDeck(
        @PathVariable(name="deck_id") String deck_id,
        @RequestBody MultiValueMap<String, String> form,
        Model model
    ){
        Integer count = Integer.parseInt(form.getFirst("draw_count"));
        Deck deck = deckSvc.drawCards(deck_id, count);

        model.addAttribute("deck",deck);
        model.addAttribute("cards",deck.getCardsList());

        return "card_game";
    }


}
