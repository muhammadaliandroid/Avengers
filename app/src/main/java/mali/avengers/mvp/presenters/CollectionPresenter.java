package mali.avengers.mvp.presenters;

import android.content.Context;
import java.util.List;
import javax.inject.Inject;

import mali.avengers.model.entities.CollectionItem;
import mali.avengers.mvp.views.View;
import mali.avengers.domain.GetCollectionUsecase;
import mali.avengers.mvp.views.CollectionView;

public class CollectionPresenter implements Presenter {
	private final GetCollectionUsecase mGetCollectionUsecase;
	private final Context mActivityContext;
	private int mCharacterId;
	private String mCollectionType;
	private CollectionView mCollectionView;

	@Inject
	public CollectionPresenter(GetCollectionUsecase getCollectionUsecase, Context activityContext) {
		mGetCollectionUsecase = getCollectionUsecase;
		mActivityContext = activityContext;
	}

	@Override public void onStart() {}

	@Override public void onStop() {}

	@Override
	public void onPause() {}

	@Override
	public void attachView(View v) {
		mCollectionView = (CollectionView) v;
	}

	@Override
	public void onCreate() {
		mGetCollectionUsecase.execute(mCollectionType).subscribe(
			this::onCollectionItemsReceived);
	}

	public void initialisePresenters(String collectionType, int characterId) {
		mCollectionType = collectionType;
		mCharacterId = characterId;
	}

	private void onCollectionItemsReceived(List<CollectionItem> items) {
		mCollectionView.hideLoadingIndicator();
		mCollectionView.showItems(items);
	}
}
