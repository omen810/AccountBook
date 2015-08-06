package com.zbc.soft;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.app.ListActivity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyListActivity extends ListActivity implements OnScrollListener {
	private ListView listView;
	private int visibleLastIndex = 0; // ���Ŀ���������
	private int visibleItemCount; // ��ǰ���ڿɼ�������
	private ListViewAdapter adapter;
	private View loadMoreView;
	private TextView loadMoreButton;
	private Handler handler = new Handler();
	private Context context = null;
	private ImageView spaceshipImage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list);
		loadMoreView = getLayoutInflater().inflate(R.layout.load_more, null);
		loadMoreButton = (TextView) loadMoreView
				.findViewById(R.id.loadMoreButton);
		listView = getListView(); // ��ȡid��list��ListView
		context = this;
		// LayoutInflater inflater = LayoutInflater.from(context);
		// View v = inflater.inflate(R.layout.load_more, null);
		spaceshipImage = (ImageView) loadMoreView.findViewById(R.id.loadImg);
		listView.addFooterView(loadMoreView); // �����б�ײ���ͼ
		initAdapter();
		setListAdapter(adapter); // �Զ�Ϊid��list��ListView����������
		listView.setOnScrollListener(this); // ��ӻ�������
		// ���ض���
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				context, R.layout.loading_animation);
		spaceshipImage.setAnimation(hyperspaceJumpAnimation);
		hyperspaceJumpAnimation.start();
	}

	/**
	 * ��ʼ��������
	 */
	private void initAdapter() {
		ArrayList<String> items = new ArrayList<String>();
		for (int i = 0; i < 15; i++) {
			items.add(String.valueOf(i + 1));
		}
		adapter = new ListViewAdapter(this, items);
	}

	/**
	 * ����ʱ������
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.visibleItemCount = visibleItemCount;
		visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
	}

	/**
	 * ����״̬�ı�ʱ������
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		int itemsLastIndex = adapter.getCount() - 1; // ���ݼ����һ�������
		int lastIndex = itemsLastIndex + 1; // ���ϵײ���loadMoreView��
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& visibleLastIndex == lastIndex) {
			// ������Զ�����,��������������첽�������ݵĴ���
			loadMoreButton.setText("loading...");
			Toast.makeText(MyListActivity.this, "��ȡ�ɹ�", Toast.LENGTH_LONG)
					.show();
			loadData();

			adapter.notifyDataSetChanged(); // ���ݼ��仯��,֪ͨadapter
			listView.setSelection(visibleLastIndex - visibleItemCount + 1); // ����ѡ����
		}
	}

	/**
	 * �����ť�¼�
	 * 
	 * @param view
	 */
	public void loadMore(View view) {

		// ʹ��ImageView��ʾ����

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {

				loadData();
				adapter.notifyDataSetChanged(); // ���ݼ��仯��,֪ͨadapter
				listView.setSelection(visibleLastIndex - visibleItemCount + 1); // ����ѡ����
				loadMoreButton.setText("load more"); // �ָ���ť����
			}
		}, 2000);
	}

	/**
	 * ģ���������
	 */
	private void loadData() {
		int count = adapter.getCount();
		for (int i = count; i < count + 10; i++) {
			adapter.addItem(String.valueOf(i + 1));
		}
	}
}
