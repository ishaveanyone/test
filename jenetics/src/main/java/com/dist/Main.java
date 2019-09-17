package com.dist;

import org.jenetics.BitChromosome;
import org.jenetics.BitGene;
import org.jenetics.Genotype;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.util.Factory;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-10 11:32
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：

 */


public class Main {
    // 2.) Definition of the fitness function.
    private static int eval(Genotype<BitGene> gt) {
        return gt.getChromosome()
                .as(BitChromosome.class)
                .bitCount();
    }

    public static void main(String[] args) {
        // 1.) Define the genotype (factory) suitable
        //     for the problem.
        Factory<Genotype<BitGene>> gtf =
                Genotype.of(BitChromosome.of(10, 0.5));

        // 3.) Create the execution environment.
        Engine<BitGene, Integer> engine = Engine
                .builder(Main::eval, gtf)
                .build();

        // 4.) Start the execution (evolution) and
        //     collect the result.
        Genotype<BitGene> result = engine.stream()
                .limit(100)
                .collect(EvolutionResult.toBestGenotype());

        System.out.println("Hello World:\n" + result);
    }
}