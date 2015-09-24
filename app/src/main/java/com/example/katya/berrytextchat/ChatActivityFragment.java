package com.example.katya.berrytextchat;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katya.berrytextchat.Model.MatrixCursorLoader;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChatActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<MatrixCursor>{

    private RecyclerView mRecyclerView;
    private MsgAdapter msgAdapter;
    private LoaderManager.LoaderCallbacks<MatrixCursor> mCallbacks;
    private static final int LOADER_ID = 1;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new MsgSeparator(getActivity()));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);

        msgAdapter = new MsgAdapter(getActivity());
        mRecyclerView.setAdapter(msgAdapter);

        mCallbacks = this;
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, mCallbacks);


        return view;
    }


    @Override
    public MatrixCursorLoader onCreateLoader(int i, Bundle bundle) {
        return new MatrixCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<MatrixCursor> loader, MatrixCursor matrixCursor) {
        if(matrixCursor!= null && msgAdapter != null) {
            msgAdapter.setData(matrixCursor);
            mRecyclerView.scrollToPosition(msgAdapter.getItemCount() - 1);
        }

    }

    @Override
    public void onLoaderReset(Loader<MatrixCursor> loader) {
        msgAdapter.setData(null);
    }

}
