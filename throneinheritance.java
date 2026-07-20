

class ThroneInheritance {
    // Stores the name of the king
    private String king;
    
    // Maps a parent's name to a list of their children's names in birth order
    private Map<String, List<String>> familyTree;
    
    // Tracks family members who have passed away
    private Set<String> deadMembers;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.familyTree = new HashMap<>();
        this.deadMembers = new HashSet<>();
        
        // Initialize the king's entry in our tree mapping
        familyTree.put(kingName, new ArrayList<>());
    }
    
    public void birth(String parentName, String childName) {
        // Find the parent's children list and append the new child
        familyTree.get(parentName).add(childName);
        
        // Initialize an empty children list for the new child
        familyTree.put(childName, new ArrayList<>());
    }
    
    public void death(String name) {
        // Simply mark the person as dead
        deadMembers.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        
        // Run a complete Pre-Order Traversal starting from the king
        traverse(king, order);
        
        return order;
    }
    
    // Non-optimized Pre-Order DFS helper method
    private void traverse(String currentPerson, List<String> order) {
        // If the current person is alive, add them to the inheritance list
        if (!deadMembers.contains(currentPerson)) {
            order.add(currentPerson);
        }
        
        // Recursively visit all children of the current person in order of birth
        List<String> children = familyTree.get(currentPerson);
        for (String child : children) {
            traverse(child, order);
        }
    }
}