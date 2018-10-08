package seedu.address.logic.AnakinCommands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinDeck;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Navigates into a deck identified using its displayed index from Anakin.
 */

public class AnakinCDCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "cd";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Navigates into the deck identified by the index number used in the displayed deck list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CD_SUCCESS = "Successfully navigated %1$s";

    private final Index targetIndex;

    public AnakinCDCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(AnakinModel anakinModel, CommandHistory history) throws CommandException {
        requireNonNull(anakinModel);
        List<AnakinDeck> lastShownList = anakinModel.getFilteredDeckList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        AnakinDeck deckToEnter = lastShownList.get(targetIndex.getZeroBased());
        // anakinModel.getAnakin().getIntoDeck(deckToEnter); //REQUIRES SUPPORT FROM MODEL
        anakinModel.commitAnakin();
        return new CommandResult(String.format(MESSAGE_CD_SUCCESS, deckToEnter));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnakinCDCommand // instanceof handles nulls
                && targetIndex.equals(((AnakinCDCommand) other).targetIndex)); // state check
    }
}

