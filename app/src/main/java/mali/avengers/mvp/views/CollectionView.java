package mali.avengers.mvp.views;

import java.util.List;

import mali.avengers.model.entities.CollectionItem;

public interface CollectionView extends View {

	void showLoadingIndicator();

	void hideLoadingIndicator();

	void showItems(List<CollectionItem> items);
}
