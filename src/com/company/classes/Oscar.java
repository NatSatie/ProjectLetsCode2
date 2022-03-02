package com.company.classes;

public class Oscar implements Comparable<Oscar>{
    private Actor actor;
    private Movie movie;
    private int actorAge;

    public Oscar(Actor actor, Movie movie, int actorAge){
        this.actor = actor;
        this.movie = movie;
        this.actorAge = actorAge;
    }

    public Actor getActor() {
        return this.actor;
    }

    public int getActorAge() {
        return this.actorAge;
    }

    public Movie getMovie() {
        return this.movie;
    }

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
