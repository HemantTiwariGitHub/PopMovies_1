package tiwari.hemant.popularmovies_1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayoutManager;
import 	android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.preference.Preference;
import android.widget.Toast;

public class MovieGridFragment extends Fragment implements MovieDataDownloaderTask.AsyncCompletionCallback{

    private static final String TAG = "PopM_GridFrag";
    private OnMovieSelectedListener mListener;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context  mContext;
    private MovieGridFragment mMovieGridFragment;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    boolean isLoadingInProgress = false;



    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener()   {

        int LOADING_THRESHOLD = 9;
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            GridLayoutManager layoutManager = ((GridLayoutManager)mRecyclerView.getLayoutManager());

            int lastPos = layoutManager.findLastVisibleItemPosition();
            int currentTotal =  mRecyclerView.getAdapter().getItemCount();
            //Toast.makeText(mContext,"Loading case" + lastPos,Toast.LENGTH_SHORT).show();

            if (lastPos + LOADING_THRESHOLD > currentTotal)
            {
                //case for loading
               // Toast.makeText(mContext,"Loading Started",Toast.LENGTH_SHORT).show();
                if (isLoadingInProgress == false) {
                    isLoadingInProgress = true;
                    Toast.makeText(mContext,"Loading Data...",Toast.LENGTH_SHORT).show();
                    MovieDataDownloaderTask downloaderTask = new MovieDataDownloaderTask(mMovieGridFragment, mContext);
                    downloaderTask.execute();
                }
                else {

                 //   Toast.makeText(mContext,"LoadingProgress",Toast.LENGTH_SHORT).show();
                }
            }


        }
    };

    MovieGridAdapter.MovieItemClickListener movieItemClickListener = new MovieGridAdapter.MovieItemClickListener() {
        @Override
        public void onItemClick(int pos) {
            Log.d(TAG, "onItemClick" + pos );
            if (mListener != null) {
                Log.d(TAG, "onItemClick inside" + pos );
                mListener.onMovieSelected(pos);
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieGridFragment = this;
        mContext = this.getContext();
    }

    @Override
    public void onResume() {
        super.onResume();

        MovieDataDownloaderTask downloaderTask = new MovieDataDownloaderTask(mMovieGridFragment, mContext);
        downloaderTask.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "OnCreateView");
        View MovieGridView = inflater.inflate(R.layout.fragment_movie_grid, container, false);

        mRecyclerView = (RecyclerView) MovieGridView.findViewById(R.id.movie_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this.getContext(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(mScrollListener);



        return MovieGridView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMovieSelectedListener) {
            mListener = (OnMovieSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //isLoadingInProgress = false
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDownloadComplete() {

        Log.d(TAG,"onDownloadComplete" );
        if (mRecyclerView.getAdapter() == null) {
            mAdapter = new MovieGridAdapter(MovieDataDownloaderTask.getMovieDetailList(), movieItemClickListener);
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();
        isLoadingInProgress = false;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMovieSelectedListener {
        // TODO: Update argument type and name
        void onMovieSelected(int pos);
    }
}
