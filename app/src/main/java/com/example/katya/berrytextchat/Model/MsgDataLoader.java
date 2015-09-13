package com.example.katya.berrytextchat.Model;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katya on 9/13/15.
 */
public class MsgDataLoader extends AsyncTaskLoader<List<Message>> {

    private List<Message> mData;

    public MsgDataLoader(Context context) {
        super(context);
    }

    @Override
    public List<Message> loadInBackground() {
        // This method is called on a background thread and should generate a
        // new set of data to be delivered back to the client.
        return MsgDataStore.INSTANCE.getList();
    }

    @Override
    public void deliverResult(List<Message> data) {
        if (isReset()) {
            releaseResources(data);
            return;
        }

        List<Message> oldData = mData;
        mData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
        if (oldData != null && oldData != data) {
            releaseResources(oldData);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }
//        if (mObserver == null) {
//            mObserver = new SampleObserver();
//            // TODO: register the observer
//        }

        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
   }

    @Override
    protected void onReset() {
        onStopLoading();
        if (mData != null) {
            releaseResources(mData);
            mData = null;
        }

        // The Loader is being reset, so we should stop monitoring for changes.
//        if (mObserver != null) {
//            // TODO: unregister the observer
//            mObserver = null;
//        }
    }

    @Override
    public void onCanceled(List<Message> data) {
        super.onCanceled(data);
        releaseResources(data);
    }

    private void releaseResources(List<Message> data) {
        // For a simple List, there is nothing to do. For something like a Cursor, we
        // would close it in this method. All resources associated with the Loader
        // should be released here.
    }

    /*********************************************************************/
    /** (4) Observer which receives notifications when the data changes **/
    /*********************************************************************/

    // NOTE: Implementing an observer is outside the scope of this post (this example
    // uses a made-up "SampleObserver" to illustrate when/where the observer should
    // be initialized).

    // The observer could be anything so long as it is able to detect content changes
    // and report them to the loader with a call to onContentChanged(). For example,
    // if you were writing a Loader which loads a list of all installed applications
    // on the device, the observer could be a BroadcastReceiver that listens for the
    // ACTION_PACKAGE_ADDED intent, and calls onContentChanged() on the particular
    // Loader whenever the receiver detects that a new application has been installed.
    // Please don’t hesitate to leave a comment if you still find this confusing! :)
   // private SampleObserver mObserver;
}

