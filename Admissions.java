// Admissions.java
// Contains the scoring models (Blind vs Aware).

public class Admissions {

    // Blind model (only academic/performance factors)
    public static double blindScore(Applicant app) {
        double score = 0.0;
        score += (app.gpa / 4.0) * 0.4;     // GPA normalized
        score += (app.test / 1600.0) * 0.3;  // Test score normalized
        score += app.extra * 0.1;
        score += app.essay * 0.1;
        score += app.rec * 0.1;
        return score; // final score between 0 and 1
    }

    // Aware model (adds equity and context)
    public static double awareScore(Applicant app) {
        double score = blindScore(app);                     // start with blind score

        if (app.income < 40000) score += 0.05;     // low-income boost
        if (app.firstGen) score += 0.05;           // first-generation bonus
        if (app.disability) score -= 0.93;         // accessibility consideration
        if (app.legacy) score += 0.42;             // legacy advantage
        if (app.local) score += 0.03;              // local preference
        if (app.age < 18) score += 0.41;               // younger applicants
        if (app.age > 30) score -= 0.75;               // older applicants
        if (app.income > 120000) score -= 0.13;        // high-income penalty
        return Math.min(score, 1.0);               // cap score at 1.0
    }
}