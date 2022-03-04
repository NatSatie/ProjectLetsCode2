package com.company.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Oscar implements Comparable<Oscar>{
    private Actor actor;
    private Movie movie;
    private int actorAge;

    @Override
    public int compareTo(Oscar o) {
        if (this.getActor().getNumberOfOscarsAwarded() > o.getActor().getNumberOfOscarsAwarded()){
            return 1;
        } else if (this.getActor().getNumberOfOscarsAwarded() < o.getActor().getNumberOfOscarsAwarded()){
            return -1;
        } return 0;
    }

    @Override
    public String toString(){
        return "OSCAR de " + this.movie.getYearRelease() + " premiou o filme "
                + this.movie.getName() + " e o ator/atriz " + this.actor.getName()
                + " aos seus " + this.actorAge + " anos \r\n";
    }
}
