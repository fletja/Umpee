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

        // ── Main 15 Courses ──────────────────────────────────────────────────

        long bob = courseRepository.insertIfNotExists("Bob-omb Battlefield", "BOB", 1, "MAIN");
        starRepository.insertIfNotExists(bob, "Big Bob-omb on the Summit", 1, false);
        starRepository.insertIfNotExists(bob, "Footrace with Koopa the Quick", 2, false);
        starRepository.insertIfNotExists(bob, "Shoot to the Island in the Sky", 3, false);
        starRepository.insertIfNotExists(bob, "Find the 8 Red Coins", 4, false);
        starRepository.insertIfNotExists(bob, "Mario Wings to the Sky", 5, false);
        starRepository.insertIfNotExists(bob, "Behind Chain Chomp's Gate", 6, false);
        starRepository.insertIfNotExists(bob, "100 Coins", 7, true);

        long whomp = courseRepository.insertIfNotExists("Whomp's Fortress", "WF", 2, "MAIN");
        starRepository.insertIfNotExists(whomp, "Chip off Whomp's Block", 1, false);
        starRepository.insertIfNotExists(whomp, "To the Top of the Fortress", 2, false);
        starRepository.insertIfNotExists(whomp, "Shoot into the Wild Blue", 3, false);
        starRepository.insertIfNotExists(whomp, "Red Coins on the Floating Isle", 4, false);
        starRepository.insertIfNotExists(whomp, "Fall onto the Caged Island", 5, false);
        starRepository.insertIfNotExists(whomp, "Flip Warps to the Top", 6, false);
        starRepository.insertIfNotExists(whomp, "100 Coins", 7, true);

        long jrb = courseRepository.insertIfNotExists("Jolly Roger Bay", "JRB", 3, "MAIN");
        starRepository.insertIfNotExists(jrb, "Plunder in the Sunken Ship", 1, false);
        starRepository.insertIfNotExists(jrb, "Can the Eel Come Out?", 2, false);
        starRepository.insertIfNotExists(jrb, "Treasure of the Ocean Cave", 3, false);
        starRepository.insertIfNotExists(jrb, "Red Coins on the Ship Afloat", 4, false);
        starRepository.insertIfNotExists(jrb, "Blast to the Stone Pillar", 5, false);
        starRepository.insertIfNotExists(jrb, "Through the Jet Stream", 6, false);
        starRepository.insertIfNotExists(jrb, "100 Coins", 7, true);

        long ccm = courseRepository.insertIfNotExists("Cool, Cool Mountain", "CCM", 4, "MAIN");
        starRepository.insertIfNotExists(ccm, "Slip Slidin' Away", 1, false);
        starRepository.insertIfNotExists(ccm, "Li'l Penguin Lost", 2, false);
        starRepository.insertIfNotExists(ccm, "Big Penguin Race", 3, false);
        starRepository.insertIfNotExists(ccm, "Frosty Slide for 8 Red Coins", 4, false);
        starRepository.insertIfNotExists(ccm, "Snowman's Lost His Head", 5, false);
        starRepository.insertIfNotExists(ccm, "Wall Kicks Will Work", 6, false);
        starRepository.insertIfNotExists(ccm, "100 Coins", 7, true);

        long bbh = courseRepository.insertIfNotExists("Big Boo's Haunt", "BBH", 5, "MAIN");
        starRepository.insertIfNotExists(bbh, "Go on a Ghost Hunt", 1, false);
        starRepository.insertIfNotExists(bbh, "Ride Big Boo's Merry-Go-Round", 2, false);
        starRepository.insertIfNotExists(bbh, "Secret of the Haunted Books", 3, false);
        starRepository.insertIfNotExists(bbh, "Seek the 8 Red Coins", 4, false);
        starRepository.insertIfNotExists(bbh, "Big Boo's Balcony", 5, false);
        starRepository.insertIfNotExists(bbh, "Eye to Eye in the Secret Room", 6, false);
        starRepository.insertIfNotExists(bbh, "100 Coins", 7, true);

        long hmc = courseRepository.insertIfNotExists("Hazy Maze Cave", "HMC", 6, "MAIN");
        starRepository.insertIfNotExists(hmc, "Swimming Beast in the Cavern", 1, false);
        starRepository.insertIfNotExists(hmc, "Elevate for 8 Red Coins", 2, false);
        starRepository.insertIfNotExists(hmc, "Metal-Head Mario Can Move!", 3, false);
        starRepository.insertIfNotExists(hmc, "Navigating the Toxic Maze", 4, false);
        starRepository.insertIfNotExists(hmc, "A-Maze-Ing Emergency Exit", 5, false);
        starRepository.insertIfNotExists(hmc, "Watch for Rolling Rocks", 6, false);
        starRepository.insertIfNotExists(hmc, "100 Coins", 7, true);

        long lll = courseRepository.insertIfNotExists("Lethal Lava Land", "LLL", 7, "MAIN");
        starRepository.insertIfNotExists(lll, "Boil the Big Bully", 1, false);
        starRepository.insertIfNotExists(lll, "Bully the Bullies", 2, false);
        starRepository.insertIfNotExists(lll, "8-Coin Puzzle with 15 Pieces", 3, false);
        starRepository.insertIfNotExists(lll, "Red-Hot Log Rolling", 4, false);
        starRepository.insertIfNotExists(lll, "Hot-Foot-It into the Volcano", 5, false);
        starRepository.insertIfNotExists(lll, "Elevator Tour in the Volcano", 6, false);
        starRepository.insertIfNotExists(lll, "100 Coins", 7, true);

        long ssl = courseRepository.insertIfNotExists("Shifting Sand Land", "SSL", 8, "MAIN");
        starRepository.insertIfNotExists(ssl, "In the Talons of the Big Bird", 1, false);
        starRepository.insertIfNotExists(ssl, "Shining Atop the Pyramid", 2, false);
        starRepository.insertIfNotExists(ssl, "Inside the Ancient Pyramid", 3, false);
        starRepository.insertIfNotExists(ssl, "Stand Tall on the Four Pillars", 4, false);
        starRepository.insertIfNotExists(ssl, "Free Flying for 8 Red Coins", 5, false);
        starRepository.insertIfNotExists(ssl, "Pyramid Puzzle", 6, false);
        starRepository.insertIfNotExists(ssl, "100 Coins", 7, true);

        long ddd = courseRepository.insertIfNotExists("Dire, Dire Docks", "DDD", 9, "MAIN");
        starRepository.insertIfNotExists(ddd, "Board Bowser's Sub", 1, false);
        starRepository.insertIfNotExists(ddd, "Chests in the Current", 2, false);
        starRepository.insertIfNotExists(ddd, "Pole-Jumping for Red Coins", 3, false);
        starRepository.insertIfNotExists(ddd, "Through the Jet Stream", 4, false);
        starRepository.insertIfNotExists(ddd, "The Manta Ray's Reward", 5, false);
        starRepository.insertIfNotExists(ddd, "Collect the Caps...", 6, false);
        starRepository.insertIfNotExists(ddd, "100 Coins", 7, true);

        long sl = courseRepository.insertIfNotExists("Snowman's Land", "SL", 10, "MAIN");
        starRepository.insertIfNotExists(sl, "Snowman's Big Head", 1, false);
        starRepository.insertIfNotExists(sl, "Chill with the Bully", 2, false);
        starRepository.insertIfNotExists(sl, "In the Deep Freeze", 3, false);
        starRepository.insertIfNotExists(sl, "Whirl from the Freezing Pond", 4, false);
        starRepository.insertIfNotExists(sl, "Shell Shreddin' for Red Coins", 5, false);
        starRepository.insertIfNotExists(sl, "Into the Igloo", 6, false);
        starRepository.insertIfNotExists(sl, "100 Coins", 7, true);

        long wdw = courseRepository.insertIfNotExists("Wet-Dry World", "WDW", 11, "MAIN");
        starRepository.insertIfNotExists(wdw, "Shocking Arrow Lifts!", 1, false);
        starRepository.insertIfNotExists(wdw, "Top O' the Town", 2, false);
        starRepository.insertIfNotExists(wdw, "Secrets in the Shallows & Sky", 3, false);
        starRepository.insertIfNotExists(wdw, "Express Elevator--Hurry Up!", 4, false);
        starRepository.insertIfNotExists(wdw, "Go to Town for Red Coins", 5, false);
        starRepository.insertIfNotExists(wdw, "Quick Race Through Downtown!", 6, false);
        starRepository.insertIfNotExists(wdw, "100 Coins", 7, true);

        long ttm = courseRepository.insertIfNotExists("Tall, Tall Mountain", "TTM", 12, "MAIN");
        starRepository.insertIfNotExists(ttm, "Scale the Mountain", 1, false);
        starRepository.insertIfNotExists(ttm, "Mystery of the Monkey Cage", 2, false);
        starRepository.insertIfNotExists(ttm, "Scary 'Shrooms, Red Coins", 3, false);
        starRepository.insertIfNotExists(ttm, "Mysterious Mountainside", 4, false);
        starRepository.insertIfNotExists(ttm, "Breathtaking View from Bridge", 5, false);
        starRepository.insertIfNotExists(ttm, "Stomp on the Thwomp", 6, false);
        starRepository.insertIfNotExists(ttm, "100 Coins", 7, true);

        long thi = courseRepository.insertIfNotExists("Tiny-Huge Island", "THI", 13, "MAIN");
        starRepository.insertIfNotExists(thi, "Pluck the Piranha Flower", 1, false);
        starRepository.insertIfNotExists(thi, "The Tip Top of the Huge Island", 2, false);
        starRepository.insertIfNotExists(thi, "Rematch with Koopa the Quick", 3, false);
        starRepository.insertIfNotExists(thi, "Five Itty Bitty Secrets", 4, false);
        starRepository.insertIfNotExists(thi, "Wiggler's Red Coins", 5, false);
        starRepository.insertIfNotExists(thi, "Make Wiggler Squirm", 6, false);
        starRepository.insertIfNotExists(thi, "100 Coins", 7, true);

        long ttc = courseRepository.insertIfNotExists("Tick Tock Clock", "TTC", 14, "MAIN");
        starRepository.insertIfNotExists(ttc, "Roll into the Cage", 1, false);
        starRepository.insertIfNotExists(ttc, "The Pit and the Pendulums", 2, false);
        starRepository.insertIfNotExists(ttc, "Get a Hand", 3, false);
        starRepository.insertIfNotExists(ttc, "Timed Jumps on Moving Bars", 4, false);
        starRepository.insertIfNotExists(ttc, "Stop Time for Red Coins", 5, false);
        starRepository.insertIfNotExists(ttc, "Scuttle Bug Fest", 6, false);
        starRepository.insertIfNotExists(ttc, "100 Coins", 7, true);

        long rr = courseRepository.insertIfNotExists("Rainbow Ride", "RR", 15, "MAIN");
        starRepository.insertIfNotExists(rr, "Cruiser Crossing the Rainbow", 1, false);
        starRepository.insertIfNotExists(rr, "The Big House in the Sky", 2, false);
        starRepository.insertIfNotExists(rr, "Coins Amassed in a Maze", 3, false);
        starRepository.insertIfNotExists(rr, "Swingin' in the Breeze", 4, false);
        starRepository.insertIfNotExists(rr, "Tricky Triangles!", 5, false);
        starRepository.insertIfNotExists(rr, "Somewhere over the Rainbow", 6, false);
        starRepository.insertIfNotExists(rr, "100 Coins", 7, true);

        // ── Bowser Stages ─────────────────────────────────────────────────────

        long bitdw = courseRepository.insertIfNotExists("Bowser in the Dark World", "BitDW", 16, "BOWSER");
        starRepository.insertIfNotExists(bitdw, "Red Coins of the Dark World", 1, false);
        starRepository.insertIfNotExists(bitdw, "Bowser of the Dark World", 2, false);

        long bitfs = courseRepository.insertIfNotExists("Bowser in the Fire Sea", "BitFS", 17, "BOWSER");
        starRepository.insertIfNotExists(bitfs, "Red Coins in the Sea of Fire", 1, false);
        starRepository.insertIfNotExists(bitfs, "Bowser of the Fire Sea", 2, false);

        long bits = courseRepository.insertIfNotExists("Bowser in the Sky", "BitS", 18, "BOWSER");
        starRepository.insertIfNotExists(bits, "Red Coins in the Sky", 1, false);
        starRepository.insertIfNotExists(bits, "Bowser in the Sky", 2, false);

        // ── Secret Stages ─────────────────────────────────────────────────────

        long pss = courseRepository.insertIfNotExists("The Princess's Secret Slide", "PSS", 19, "SECRET");
        starRepository.insertIfNotExists(pss, "The Princess's Secret Slide", 1, false);
        starRepository.insertIfNotExists(pss, "The Princess's Secret Slide (Under 21 seconds)", 2, false);

        long sa = courseRepository.insertIfNotExists("The Secret Aquarium", "SA", 20, "SECRET");
        starRepository.insertIfNotExists(sa, "Collect the Coins Above the Ceiling", 1, false);

        long towc = courseRepository.insertIfNotExists("Tower of the Wing Cap", "ToWC", 21, "SECRET");
        starRepository.insertIfNotExists(towc, "Tower of the Wing Cap", 1, false);

        long cotmc = courseRepository.insertIfNotExists("Cavern of the Metal Cap", "CotMC", 22, "SECRET");
        starRepository.insertIfNotExists(cotmc, "Cavern of the Metal Cap", 1, false);

        long vcutm = courseRepository.insertIfNotExists("Vanish Cap under the Moat", "VCutM", 23, "SECRET");
        starRepository.insertIfNotExists(vcutm, "Vanish Cap under the Moat", 1, false);

        long wmotr = courseRepository.insertIfNotExists("Winged Mario over the Rainbow", "WMotR", 24, "SECRET");
        starRepository.insertIfNotExists(wmotr, "Winged Mario over the Rainbow", 1, false);
    }
}
