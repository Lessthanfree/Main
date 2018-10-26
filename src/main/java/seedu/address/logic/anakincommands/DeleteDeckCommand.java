package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.AddressbookMessages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.anakincommands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.anakindeck.Deck;

/**
 * Deletes a deck identified using it's displayed index from Anakin.
 */
public class DeleteDeckCommand extends Command {

    public static final String COMMAND_WORD = "deldeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the deck identified by the index number used in the displayed deck list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DECK_SUCCESS = "Deleted Deck: %1$s";

    private final Index targetIndex;

    public DeleteDeckCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Deck> lastShownList = model.getFilteredDeckList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AddressbookMessages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        Deck deckToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteDeck(deckToDelete);
        model.commitAnakin();
        return new CommandResult(String.format(MESSAGE_DELETE_DECK_SUCCESS, deckToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteDeckCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteDeckCommand) other).targetIndex)); // state check
    }
}
