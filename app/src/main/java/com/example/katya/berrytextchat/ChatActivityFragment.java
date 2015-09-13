package com.example.katya.berrytextchat;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katya.berrytextchat.Model.MsgDataStore;



/**
 * A placeholder fragment containing a simple view.
 */
public class ChatActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView mRecyclerView;
    private MsgAdapter msgAdapter;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private static final int LOADER_ID = 1;
    private static final String[] PROJECTION = new String[]{"_id", "text", "date"};
    private static final Uri CONTENT_URI;

    static {
        CONTENT_URI = Uri.parse("content://com.example.katya.berrytextchat");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new MsgSeparator(getActivity()));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        //manager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(manager);

        msgAdapter = new MsgAdapter(getActivity(), MsgDataStore.INSTANCE.getList());
        MsgDataStore.INSTANCE.setRecyclerAdapter(msgAdapter);
        mRecyclerView.setAdapter(msgAdapter);

        mCallbacks = this;
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, mCallbacks);

        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(),CONTENT_URI, PROJECTION,
                null,null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            if(loader.getId()==LOADER_ID) {
                msgAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(MsgDataStore.INSTANCE.getList().size()-1);
            }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        msgAdapter.notifyDataSetChanged();
    }

}
