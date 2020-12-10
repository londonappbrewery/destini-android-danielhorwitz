package com.londonappbrewery.destini;

public class StoryNode {

    private int mNodeKey;
    private int mStoryStringId;
    private int mAnswerOneStringId;
    private StoryNode mAnswerOneNextNode;
    private int mAnswerTwoStringId;
    private StoryNode mAnswerTwoNextNode;
    private StoryNode mNextNode;

    static public StoryNode getRootNode() {
        return buildNodeTree();
    }

    static public StoryNode getSpecifiedNode(int nodeKey) {
        int myKey = Math.max(nodeKey, 1);
        for (StoryNode curNode = buildNodeTree(); curNode != null; curNode = curNode.mNextNode) {
            if (curNode.mNodeKey == myKey) {
                return curNode;
            }
        }
        return null;
    }

    public int getStoryStringId() {
        return mStoryStringId;
    }

    public int getAnswerOneStringId() {
        return mAnswerOneStringId;
    }

    public int getAnswerTwoStringId() {
        return mAnswerTwoStringId;
    }

    public int getNodeKey() {
        return mNodeKey;
    }

    // Get the next node for the answer.
    // Param -
    //  followAnswerOne - True if following answer one, false if following answer two.
    // Returns -
    //  The appropriate node for the answer. Returns null if a leaf node.
    public StoryNode nextNodeForAnswer(boolean followAnswerOne) {
        return followAnswerOne ? mAnswerOneNextNode : mAnswerTwoNextNode;
    }

    // Is this a leaf node?
    // Returns -
    //  True if a leaf node, false if there are answers to follow.
    public boolean isLeafNode() {
        return (mAnswerOneNextNode == null);
    }

    private StoryNode(int nodeKey,
                      int storyStringId,
                      int answerOneStringId,
                      StoryNode answerOneNextNode,
                      int answerTwoStringId,
                      StoryNode answerTwoNextNode,
                      StoryNode nextNode) {
        mNodeKey = nodeKey;
        mStoryStringId = storyStringId;
        mAnswerOneStringId = answerOneStringId;
        mAnswerOneNextNode = answerOneNextNode;
        mAnswerTwoStringId = answerTwoStringId;
        mAnswerTwoNextNode = answerTwoNextNode;
        mNextNode = nextNode;
    }

    static private StoryNode buildNodeTree() {
        StoryNode t6 = new StoryNode(6, R.string.T6_End, 0, null, 0, null, null);
        StoryNode t5 = new StoryNode(5, R.string.T5_End, 0, null, 0, null, t6);
        StoryNode t4 = new StoryNode(4, R.string.T4_End, 0, null, 0, null, t5);
        StoryNode t3 = new StoryNode(3, R.string.T3_Story, R.string.T3_Ans1, t6, R.string.T3_Ans2, t5, t4);
        StoryNode t2 = new StoryNode(2, R.string.T2_Story, R.string.T2_Ans1, t3, R.string.T2_Ans2, t4, t3);
        return new StoryNode(1, R.string.T1_Story, R.string.T1_Ans1, t3, R.string.T1_Ans2, t2, t2);
    }
}
