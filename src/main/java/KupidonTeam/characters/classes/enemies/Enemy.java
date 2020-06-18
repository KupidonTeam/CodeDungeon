package KupidonTeam.characters.classes.enemies;

import KupidonTeam.characters.classes.Stats;
import KupidonTeam.characters.classes.skills.Skill;
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
    public String toString() {


        StringBuilder attacks = new StringBuilder();
        for (int i = 0; i < skillList.size(); i++) {
            attacks.append(skillList.get(i).toString());
            if (i != skillList.size() - 1) attacks.append(",");
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
                attacks.toString(),
                description,
                name,
                stats.toString(),
                vulnerabilities);


    }
}
