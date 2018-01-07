package com.ahmedkhames.bitbucket.GitHubRepoList;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemLongClick;
import  com.ahmedkhames.bitbucket.BaseActivity;
import  com.ahmedkhames.bitbucket.GitHubAPI.Response.GitHubAPIRepoResponse;
import  com.ahmedkhames.bitbucket.MyApplication;
import  com.ahmedkhames.bitbucket.R;
import  com.ahmedkhames.bitbucket.Utils.ListViewEndlessScrollListeter;


public class MainActivity extends BaseActivity implements GitHubRepoListContract.View{

    @BindView(R.id.gitHubRepoList_lvRepo)
    ListView mLvRepo;

    @BindView(R.id.gitHubRepoList_PB)
    com.github.lzyzsd.circleprogress.DonutProgress mPB;

    @Inject
    GitHubRepoListPresenter mGitHubRepoPresenter;
    @Inject
    Application mApplication;

    private GitHubRepoListAdapter mGitHubRepoAdapter;
    private static final int INITIAL_PAGE = 0;
    private static final int REPO_PER_PAGE = 10;
    private AlertDialog mAlertDialog;
    private static int SCREEN_ORIENTATION=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_repo_list);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            String reload = savedInstanceState.getString("reload");

        }else{
            mGitHubRepoPresenter.callRepoAPI(mApplication.getString(R.string.git_user_name),INITIAL_PAGE,REPO_PER_PAGE);

            mGitHubRepoAdapter = new GitHubRepoListAdapter(this, new ArrayList<GitHubAPIRepoResponse>());
            mLvRepo.setAdapter(mGitHubRepoAdapter);


            mLvRepo.setOnScrollListener(new ListViewEndlessScrollListeter() {
                @Override
                public boolean onLoadMore(int page, int totalItemsCount) {
                    mGitHubRepoPresenter.callRepoAPI(mApplication.getString(R.string.git_user_name),page,REPO_PER_PAGE);
                    return true;
                }
            });
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("reload", "true");
    }

    @OnItemLongClick(R.id.gitHubRepoList_lvRepo)
    public boolean onRepositoryLongClick(int position){
        GitHubAPIRepoResponse clickedRepoObj = mGitHubRepoAdapter.getItem(position);
        mGitHubRepoPresenter.listViewLongClick(clickedRepoObj);
        return true;
    }

    @Override
    protected void setupActivityComponent() {
        MyApplication.get(this)
                .getmAppComponent()
                .inject(new GitHubRepoListModule(this))
                .inject(this);
    }


    @Override
    public void loadRepo(List<GitHubAPIRepoResponse> repoList) {
        //mGitHubRepoAdapter.clear();
        mGitHubRepoAdapter.addAll(repoList);
    }


    @Override
    public void showPB(boolean isDisplay) {
        mPB.setVisibility(isDisplay ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showAlertDialog(GitHubAPIRepoResponse response) {
        // SpannableString is the class for text whose content is immutable but to which
        // markup objects can be attached and detached.
        final SpannableString htmlUrl =
                new SpannableString(response.getOwnerResponse().getOwnerHMLURL() +"\n\n"+response.getRepoHTMLURL());
        Linkify.addLinks(htmlUrl, Linkify.WEB_URLS);



        mAlertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.go_to_url)
                .setCancelable(true)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton(R.string.cancel, null)
                .setMessage(htmlUrl)
                .create();

        mAlertDialog.show();

        ((TextView)mAlertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());

    }
}
