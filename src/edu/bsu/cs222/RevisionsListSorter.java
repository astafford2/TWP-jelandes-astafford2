package edu.bsu.cs222;

import java.util.*;

class RevisionsListSorter {
    List<Revision> revisionsSort(List<Revision> revisionsList) {
        List<String> usernames = new ArrayList<>();
        for(Revision rev : revisionsList) {
            usernames.add(rev.username);
        }

        Set<String> usernamesSet = new HashSet<>(usernames);
        usernames = new ArrayList<>(usernamesSet);

        Map<String, Integer> revisionCounts = new HashMap<>();
        for(String user : usernames) {
            Integer revisionsCount = 0;
            for(Revision rev : revisionsList) {
                if(user.equals(rev.username)) {
                    revisionsCount++;
                }
            }
            revisionCounts.put(user, revisionsCount);
        }

        List<Map.Entry<String, Integer>> sortRevisionList = new ArrayList<>(revisionCounts.entrySet());
        sortRevisionList.sort((user1, user2) -> (user2.getValue() - user1.getValue()));

        List<String> multRevUsernames = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : sortRevisionList) {
            if(entry.getValue() != 1) {
                multRevUsernames.add(entry.getKey());
            }
        }

        List<Revision> sortedRevisionsList = new ArrayList<>();

        for(String multRevUser : multRevUsernames) {
            for(Revision rev : revisionsList) {
                if(multRevUser.equals(rev.username)) {
                    String username = rev.username;
                    String timestamp = rev.timestamp;
                    Revision newRevision = new Revision(username, timestamp);
                    sortedRevisionsList.add(newRevision);
                }
            }
        }

        for(Revision rev : revisionsList) {
            if(!multRevUsernames.contains(rev.username)) {
                String username = rev.username;
                String timestamp = rev.timestamp;
                Revision newRevision = new Revision(username, timestamp);
                sortedRevisionsList.add(newRevision);
            }
        }

        return sortedRevisionsList;
    }
}