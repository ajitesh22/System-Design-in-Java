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
        return tweetId;
    }
    int getuserId(){
        return userId;
    }
    int gettimeStamp(){
        return tweetId;
    }

}

class SeqTime {
    int time;
    public SeqTime(){
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
    private Map<Integer,Set<Integer>> followersList;
    private Map<Integer,PriorityQueue<Tweet>> userTweets;
    SeqTime seqTime;
    public Twitter() {
        userTweets = new HashMap<>();
        followersList = new HashMap<>();
        seqTime = new SeqTime();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(userId , tweetId , seqTime.getTime());
        Set<Integer> followers = followersList.getOrDefault(userId,new HashSet<>());
        followers.add(userId);
        followersList.put(userId,followers);
        PriorityQueue<Tweet> tweets = userTweets.getOrDefault(userId , new PriorityQueue<>(new MyTweetsComparator()));
        tweets.add(tweet);
        userTweets.put(userId,tweets);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        
        Set<Integer> followers = followersList.get(userId);
        if(followers == null || followers.isEmpty()){
            return new ArrayList<>() ;
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
        PriorityQueue<Tweet> top10Tweets = new PriorityQueue<>(new MyTweetsComparator());
        for(int i=0;i<10;i++){
            if(tweets==null || tweets.isEmpty()){
                break;
            }
            top10Tweets.offer(tweets.poll());
        }
        
        return top10Tweets;
    }
    
        private List<Integer> mergeTweets(Map<Integer, PriorityQueue<Tweet>> tweetLists) {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<Tweet> finalPQ = new PriorityQueue<>(new MyTweetsComparator());
        for (Integer userId : tweetLists.keySet()) {
            PriorityQueue<Tweet> tweets = tweetLists.get(userId);
            Tweet top = tweets.poll();
            //tweetLists.put(userId, tweets);
            finalPQ.offer(top);
        }
         
        int count = 0;
        while (count < 10 && !tweetLists.isEmpty()) {
            Tweet curr = finalPQ.poll();
            ans.add(curr.tweetId);
            PriorityQueue<Tweet> nextTweetList = tweetLists.get(curr.userId);
             
            if (!nextTweetList.isEmpty()) {
                finalPQ.offer(nextTweetList.poll());
            } else {
                tweetLists.remove(curr.userId);
            }
            count += 1;
        }
         
        return ans;
    }
    
}


class MyTweetsComparator implements Comparator<Tweet> {
    @Override
    public int compare(Tweet a , Tweet b){
        return b.timeStamp - a.timeStamp;
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
