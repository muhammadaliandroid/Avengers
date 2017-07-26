package mali.avengers.domain;

import java.util.List;
import javax.inject.Inject;

import mali.avengers.model.entities.CollectionItem;
import mali.avengers.model.repository.Repository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetCollectionUsecase implements Usecase<List<CollectionItem>> {
	private final Repository mRepository;
	private final int mCharacterId;

	@Inject public GetCollectionUsecase(int characterId, Repository repository) {
		mRepository = repository;
		mCharacterId = characterId;
	}

	@Override
	public Observable<List<CollectionItem>> execute() {
		return null;
	}

	public Observable<List<CollectionItem>> execute(String type) {
		if (!type.equals(CollectionItem.COMIC) && !type.equals(CollectionItem.EVENT) && !type.equals(CollectionItem.SERIES) && !type.equals(CollectionItem.STORY))
			throw new IllegalArgumentException("Collection type must be events|series|comics|stories");

		return mRepository.getCharacterCollection(mCharacterId, type)
			.subscribeOn(Schedulers.newThread())
			.flatMap(collectionItems -> {

				for (CollectionItem  coolectionItem: collectionItems) {
					System.out.println("[DEBUG]" + " GetCollectionUsecase execute - " +
					    ""+coolectionItem);
				}

				return Observable.just(collectionItems);
			})
			.observeOn(AndroidSchedulers.mainThread());
	}
}
