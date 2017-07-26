/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers.mvp.views;

import android.app.ActivityOptions;
import java.util.List;
import mali.avengers.model.entities.Character;

@SuppressWarnings("unused")
public interface CharacterListView extends View {

    void bindCharacterList(List<Character> avengers);

    void showCharacterList();

    void hideCharactersList();

    void showLoadingMoreCharactersIndicator();

    void hideLoadingMoreCharactersIndicator();

    void hideLoadingIndicator ();

    void showLoadingView();

    void hideLoadingView();

    void showLightError();

    void hideErrorView();

    void showEmptyIndicator();

    void hideEmptyIndicator();

    void updateCharacterList(int charactersLimit);

    ActivityOptions getActivityOptions(int position, android.view.View clickedView);

    void showConnectionErrorMessage();

    void showServerErrorMessage();

    void showUknownErrorMessage();

    void showDetailScreen(String characterName, int characterId);
}
