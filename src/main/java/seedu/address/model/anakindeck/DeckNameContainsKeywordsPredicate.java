package seedu.address.model.anakindeck;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Deck}'s {@code Name} matches any of the keywords given.
 */
public class DeckNameContainsKeywordsPredicate implements Predicate<Deck> {
    private final List<String> keywords;

    public DeckNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Deck deck) {
        return keywords.stream()
                   .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(deck.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof DeckNameContainsKeywordsPredicate // instanceof handles nulls
                           && keywords.equals(((DeckNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
