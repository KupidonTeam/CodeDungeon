package KupidonTeam.dungeon;

public class Dungeon {
    String jsonDungeon;

    protected Dungeon(String jsonDungeon) {
        if (!jsonDungeon.isEmpty())
            this.jsonDungeon = jsonDungeon;
    }
}
