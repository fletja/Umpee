package com.sm64tracker.service;

import com.sm64tracker.repository.CourseRepository;
import com.sm64tracker.repository.StarRepository;

public class SeedDataLoader {
    private static final boolean SEED_ENABLED = true;

    public static void seedIfNeeded() {
        if (!SEED_ENABLED) {
            return;
        }

        CourseRepository courseRepository = new CourseRepository();
        StarRepository starRepository = new StarRepository();

        long bob = courseRepository.insertIfNotExists("Bob-omb Battlefield", "BOB", 1);
        starRepository.insertIfNotExists(bob, "Big Bob-omb on the Summit", 1, false);
        starRepository.insertIfNotExists(bob, "Footrace with Koopa the Quick", 2, false);
        starRepository.insertIfNotExists(bob, "Shoot to the Island in the Sky", 3, false);
        starRepository.insertIfNotExists(bob, "Find the 8 Red Coins", 4, false);
        starRepository.insertIfNotExists(bob, "Mario Wings to the Sky", 5, false);
        starRepository.insertIfNotExists(bob, "Behind Chain Chomp's Gate", 6, false);
        starRepository.insertIfNotExists(bob, "100 Coins", 7, true);

        long whomp = courseRepository.insertIfNotExists("Whomp's Fortress", "WF", 2);
        starRepository.insertIfNotExists(whomp, "Chip off Whomp's Block", 1, false);
        starRepository.insertIfNotExists(whomp, "To the Top of the Fortress", 2, false);
        starRepository.insertIfNotExists(whomp, "Shoot into the Wild Blue", 3, false);
        starRepository.insertIfNotExists(whomp, "Red Coins on the Floating Isle", 4, false);
        starRepository.insertIfNotExists(whomp, "Fall onto the Caged Island", 5, false);
        starRepository.insertIfNotExists(whomp, "Flip Warps to the Top", 6, false);
        starRepository.insertIfNotExists(whomp, "100 Coins", 7, true);

        long jrb = courseRepository.insertIfNotExists("Jolly Roger Bay", "JRB", 3);
        starRepository.insertIfNotExists(jrb, "Eerie Inlet from the Pirate's Hideout", 1, false);
        starRepository.insertIfNotExists(jrb, "Can the Eel Come Out?", 2, false);
        starRepository.insertIfNotExists(jrb, "Treasure of the Ocean Cave", 3, false);
        starRepository.insertIfNotExists(jrb, "Red Coins on the Ship Afloat", 4, false);
        starRepository.insertIfNotExists(jrb, "Secret of the Haunted Ship", 5, false);
        starRepository.insertIfNotExists(jrb, "Blast to the Stone Pillar", 6, false);
        starRepository.insertIfNotExists(jrb, "100 Coins", 7, true);

        long ccm = courseRepository.insertIfNotExists("Cool, Cool Mountain", "CCM", 4);
        starRepository.insertIfNotExists(ccm, "Slidin' Down the Mountain", 1, false);
        starRepository.insertIfNotExists(ccm, "Li'l Penguin Lost", 2, false);
        starRepository.insertIfNotExists(ccm, "Big Penguin Race", 3, false);
        starRepository.insertIfNotExists(ccm, "Frosty Slide for 8 Red Coins", 4, false);
        starRepository.insertIfNotExists(ccm, "Snowman's Land", 5, false);
        starRepository.insertIfNotExists(ccm, "Wall Kicks Will Work", 6, false);
        starRepository.insertIfNotExists(ccm, "100 Coins", 7, true);
    }
}
