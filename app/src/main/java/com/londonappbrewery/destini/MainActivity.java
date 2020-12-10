package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private StoryNode mCurrentStoryNode;
    private TextView mStoryTextView;
    private Button mResetButton;
    private Button mAnswerOneButton;
    private Button mAnswerTwoButton;

    // Display the current story state.
    private void displayCurState() {
        mStoryTextView.setText(mCurrentStoryNode.getStoryStringId());
        int visibility;
        if (mCurrentStoryNode.isLeafNode()) {
            visibility = View.INVISIBLE;
        } else {
            visibility = View.VISIBLE;
            mAnswerOneButton.setText(mCurrentStoryNode.getAnswerOneStringId());
            mAnswerTwoButton.setText(mCurrentStoryNode.getAnswerTwoStringId());
        }
        mAnswerOneButton.setVisibility(visibility);
        mAnswerTwoButton.setVisibility(visibility);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore state.
        mCurrentStoryNode =
                (savedInstanceState == null) ?
                        StoryNode.getRootNode() :
                        StoryNode.getSpecifiedNode(savedInstanceState.getInt("StoryNodeKey", 0));

        // Wire up the 3 views from the layout to the member variables.
        mStoryTextView = findViewById(R.id.storyTextView);
        mResetButton = findViewById(R.id.resetButton);
        mAnswerOneButton = findViewById(R.id.buttonTop);
        mAnswerTwoButton = findViewById(R.id.buttonBottom);

        // Define a listener for the top button.
        mAnswerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentStoryNode = mCurrentStoryNode.nextNodeForAnswer(true);
                displayCurState();
            }
        });

        // Define a listener for the bottom button.
        mAnswerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentStoryNode = mCurrentStoryNode.nextNodeForAnswer(false);
                displayCurState();
            }
        });

        // Define a listener for resetting the state.
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentStoryNode = StoryNode.getRootNode();
                displayCurState();
            }
        });

        // Provide initial display.
        displayCurState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("StoryNodeKey", mCurrentStoryNode.getNodeKey());
    }
}
