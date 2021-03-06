package edu.pse.beast.celectiondescriptioneditor.UserActions;

import edu.pse.beast.celectiondescriptioneditor.CElectionDescriptionEditor;
import edu.pse.beast.toolbox.UserAction;

/**
 * @author NikolaiLMS
 */
public class ElectionPasteUserAction extends UserAction {

    private final CElectionDescriptionEditor electionEditor;

    public ElectionPasteUserAction(CElectionDescriptionEditor electionEditor) {
        super("paste");
        this.electionEditor = electionEditor;
    }

    @Override
    public void perform() {
        electionEditor.getCodeArea().getUserActionList().getActionById("paste").perform();
    }
}
