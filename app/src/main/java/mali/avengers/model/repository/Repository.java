/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers.model.repository;

import java.util.List;

import mali.avengers.model.entities.CollectionItem;
import rx.Observable;
import mali.avengers.model.entities.Character;

public interface Repository {
    Observable<Character> getCharacter (final int characterId);

    Observable<List<Character>> getCharacters (int offset);

    Observable<List<CollectionItem>> getCharacterCollection(int characterId, String type);
}
