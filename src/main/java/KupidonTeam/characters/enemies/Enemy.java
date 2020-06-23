package KupidonTeam.characters.enemies;

import KupidonTeam.characters.Stats;
import KupidonTeam.characters.skills.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Enemy {
    private int enemyId;
    private String name;
    private String description;
    private int level;
    private Stats stats;
    private List<Skill> skillList;
    private String vulnerabilities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enemy enemy = (Enemy) o;
        return enemyId == enemy.enemyId;
    }

    @Override
    public String toString() {
        StringBuilder attacks = new StringBuilder();

        for (int i = 0; i < skillList.size(); i++) {
            attacks.append(skillList.get(i));

            if (i != skillList.size() - 1) {
                attacks.append(",");
            }
        }

        return String.format("" +
                        "" +
                        "\"attacks\" : { %s}," +
                        "\"desc\" : \"%s\"," +
                        "\"inventory\" : {}," +
                        "\"name\" : \"%s\"," +
                        "\"stats\" : {%s}," +
                        "\"vulnerabilities\" : %s" +
                        "",
                attacks,
                description,
                name,
                stats,
                vulnerabilities);
    }

}
