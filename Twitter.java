class Tweet {
    int tweetId ;
    int userId;
    int timeStamp;
    public Tweet(int _tweetId , int _userId ,int _timeStamp){
        tweetId = _tweetId;
        userId = _userId;
        timeStamp =_timeStamp;
    }

    int getTweetId(){
        return TweetId;
    }
    int getuserId(){
        return userId;
    }
    int gettimeStamp(){
        return tweetId;
    }

}

class seqTime {
    int time;
    public seqTime(){
        time=0;
    }
    public int getTime(){
        int currTime = time;
        time+=1;
        return currTime;
    }
}



class Twitter {

    /** Initialize your data structure here. */
    private map<Integer,Set<Integer>> followersList;
    private map<Integer,PriorityQueue<Tweet>> userTweets;
    public Twitter() {
        userTweets = new HashMap<>();
        followersList = new HashMap<>();
        seqTime = new SeqTime();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(userId , tweetId , seqTime.getTime());
        Set<Integer> followers = followersList.getOrDefault(userId,new HashSet<>());
        follower.add(userId);
        followersList.put(userId,followers);
        PriorityQueue<Tweet> tweets = userTweets.getOrDefault(userId , new PriorityQueue<>(new myTweetComparator()));
        tweets.add(tweet);
        userTweets.put(userId,tweets);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        
        Set<Integer> followers = followersList.get(userId);
        if(followers == null || followers.isEmpty()){
            return;
        }
        Map<Integer,PriorityQueue<Tweet>> tweetList = new HashMap<>();
        for(Integer follower : followers){
            PriorityQueue<Tweet> tweets = userTweets.get(follower);
            //TODO implementation of getTop10Tweets
            PriorityQueue<Tweet> top10List = getTop10Tweets(tweets);
            Iterator<Tweet> it = top10List.iterator();
            if(!top10List.isEmpty()){
                tweetList.put(follower,top10List);
            }
        }
        //TODO implementation of mergeTweets
        return mergeTweets(tweetList);
          
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followers = followersList.getOrDefault(followeeId,new HashSet<>());
        followers.add(followerId);
        followers.add(followeeId);
        followersList.put(followerId , followers);
        
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        
        if(followerId == followeeId){
            return;
        }
        Set<Integer> followers = followersList.getOrDefault(followeeId,new HashSet<>());
        followers.remove(followeeId);
        followersList.put(followerId , followers); 
    }
    
    
    public PriorityQueue<Tweet>  getTop10Tweets(PriorityQueue<Tweet> tweets){
        
        
    }
}


class MyTweetsComparator implements Comparator<Tweet> {
    @Override
    public int compare(Tweet a , Tweet b){
        return b.timestamp - a.timestamp;
    }
}


/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
