# wAlnut

"*Though we are limited physically. Our spirit is limitless.*"

~ [Wenqi Jia](https://www.youtube.com/watch?v=Mx6hBgNNacE)

#### Table of Contents
- **[Why and Goals](#why-and-goals)**
- **[How](#how)**
    + **[How to Develop the Confidence to Work on this Project](#how-to-develop-the-confidence-to-work-on-this-project])**
    + **[How to Contribute](#how-to-contribute)**
    + **[Setup Development Environment for Mac](#setup-development-environment-for-mac)**
- **[What](#what)**
    + **[Introduction](#introduction)**
    + **[Key Theories](#key-theories)**
    + **[Code Outline](#code-outline)**

## Why and Goals

I believe that intelligent machines will be able to solve many of our
hardest problems and will cause many new hard problems. Our hardest problems 
like scarcity of water, food, shelter, resources, understanding, affection and
mysteries like cancer, mortality, and secretes of the universe involve
understanding a lot of data and as humans we are limited by the processing
power and size of our brains. But if we figure out the computational principles
that make you and I intelligent, simulate them on a computer, scale it up to
surpass the collective intelligence of the 7+ billion people on Earth we will
have created intelligent machines that do not need to eat, sleep, or reproduce
that can work on our hardest problems much more efficiently than humans.

Technology has always been used to do great good and great evil and even if we 
have the best intentions to do good, intelligent machines will cause many new 
problems. For example, there will be massive job loss since most jobs 
can be replaced by intelligent machines. A possible solution to this hard 
problem will be to augment our brains and become super intelligent as a species.

The current short term goal is to experiment with a simplified visual pathway 
from an eye that sees binary bitmap training videos to experimental general
learning algorithms to understand the key principles of intelligence and discover
new principles of intelligence that evolution missed. This repo is currently
an object oriented Python implementation of the computational framework
outlined in Dileep George's PhD thesis [*How the Brain Might Work: A Hierarchical and Temporal Model for Learning and Recognition*](https://github.com/WalnutiQ/papers/blob/master/HowTheBrainMightWork.pdf)

The long term goal of this project is to build a machine that exceeds human
intelligence to solve our hardest problems. If you are interested in investing
in this project please e-mail quinnliu@vt.edu

~ Q Liu

## How

## How to Develop the Confidence to Work on a Project so Seemingly Hard

Start with the most fundamental truths and reason up from there.

Then watch this video on [developing true confidence by Kong](https://www.youtube.com/watch?v=xSURldy2EJE).  
Then watch this video on [how to live by Steve](https://www.youtube.com/watch?v=UF8uR6Z6KLc).  
Then watch this video on [how to live by Neil](https://www.youtube.com/watch?v=ikAb-NYkseI).

The best approach I have come across for building an artificial general intelligence
(AGI) are summarized in this [video by Dileep](https://www.youtube.com/watch?v=NPtAIVieh4k) and
is the same approach used for this project.

The following are the key points:

1. **_When looking at neuroscience for inspiration consider adding something to your computational
   framework if it is present in reality and neurobiology._**
     - This is because the neurons in your brain are trying to do 3 things:
         + 1) stay alive
         + 2) communicate with other parts of the brain limited by the evolved neurobiology
           at the time
         + 3) information processing
     - The information processing is the only part we want to add to our model.
       Unfortunately, elements of neurobiology are not cleanly split. For example,
       a neuron spike is not present in reality and it might be used for
       information processing but is more likely to be the only way 2 neurons can
       communicate with each other. We must remember to focus on discovering the
       principles of intelligence instead of getting lost in the details of modeling
       everything a brain does. Modeling how a neuron stays alive and communicates
       with other parts of the brain is not useful for information processing and will
       overcomplicate the model.

2. **_Solve the same problem at different levels of complexity starting at the
   simplest complexity level to discover general principles of intelligence._**
     - **Flying Example:**
       When the Wright brothers were building their flying machine they
       tried to solve the problem of controlling the machine in the air
       by starting with flying a kite, then controlling a glider, and then
       using a wind tunnel. In each case they were trying to solve the same
       problem at different complexity levels.
     - **AGI Example 1:**
       When trying to recognize what is in a grayscale image that is 1000 by 1000
       pixels, instead try to recognize what is in a grayscale image that is 32 by
       32 pixels. When that is too hard try to recognize what is in a binary
       32 by 32 pixel image.

3. **_For each principle of general intelligence produced by neurobiology try to use a
   more efficient substitute from other fields if there is a mapping._**
     - **Flying Example:**
       When the Wright brothers were building how to control their flying
       machine they did not build a plane with a bird's wing. When they were
       trying to replicate the banking behavior of a bird's wing they substituted
       the bird's wing with a technique called wing warping that adjusts the wing
       flaps up or down on each side of the wing so that an airplane can efficiently
       bank.
     - The model should not model every anatomical detail of the
       brain but instead will strive to use the best substitutes to model the
       principles of general intelligence. This is analogous to how a plane and
       helicopter do not model every anatomical detail of a bird's wing but instead
       strive to use the best substitutes to model the principles of general flight.

4. **_Do not try to find all principles of general intelligence from neuroscience._**
     - This is because if you do you are assuming that the human brain has discovered
       all the principles to general intelligence which is very wrong. Evolution
       accidentally discovered several principles of general intelligence over
       hundreds of thousands of years. There are definitely some principles it
       has yet to discover.

5. **_You can solve one problem independently before solving another problem._**
      - **Flying Example:**
        The Wright Brothers solved the problem of flight control with wind warping
        before even starting to solve the problem of efficient enough engine.
      - We can understand principles of intelligence before we have a detailed
        video of how networks of neurons interact with each other.
      - We can understand principles of hierarchy and time in intelligence before having
        A fully sensory motor theory.

The following are additional strategies being used that are not presented in the video:

1. **_Say there is idea A and idea B that contradict each other. The idea that has more people moving
   from one idea to the other is the more likely to be truth idea._**
     - 1) Every human is susceptible to believing in wrong ideas this since there is no free will.
     - 2) In my past I have believed in ideas that are false because I have no control over the information
       I am presented. For example I have believed:
       + Santa is real, if you eat earwax you go deaf, there is a god that looks similar to humans,
         there is free will, you are born with confidence and can't learn how to be confident, ...
     - **Example:**
       Say there is idea A and idea B that contradict each other. And you notice that the number of people that
       strongly believe idea A after having something happen to them decide to strongly believe in idea B is more than
       the other way around. THEN it is pretty likely idea B is more truthful than idea A. However this does not mean
       idea B is completely true.
     - **AI Example:** Right now the # of machine/deep learning researchers that are changing their approach from using
       Classical Machine Learning Techniques like deep neural nets(Idea A) and deciding to try a new approach to AGI
       called Probabilistic Graphical Models(Idea B) is much greater than the # of people going from Idea B -> Idea A.
       + This is a list of [29 serious AI researchers](http://www.vicarious.com/about.html) that originally believed in
         Idea A but then after learning about Idea B decided that Idea B is much better.
       + I know of 0 AI researchers that originally strongly believed in Idea B then after learning about Idea A
         decided that Idea A is much better.

2. **_Currently we are missing a tool that visualizes a model of the brain across time extremely efficiently on
      intelligence metrics that really matter._**
     - Currently the best way to view information processing across time of an general intelligent machine is using fMRI.
       Unfortunately, machines like fMRI show delayed high level brain activity for brain metrics that are not
       necessary for a general AI including:
       + 1) how neurons stay alive through movement of oxygen throughout the brain.
       + 2) how neurons communicate with other parts of the brain limited by the evolved neurobiology
           at the time
       + The fMRI metrics for how an intelligent machine is doing information processing are rarely shown.
     - So what are the intelligence metrics that really matter?
       + Intelligence metrics that really matter have to be replicable and make clear how a lot of information is
         being processed and stored to produce certain results.
         + These results include:
           + 1) Being able to answer questions that require an accurate model of the world.
             + One example is to answer questions where the task is to answer which of 2 statements are nonsense.
               + Statement 1) After waiting barefoot in the lake Eric used his shirt to dry his feet.
               + Statement 2) After waiting barefoot in the lake Eric used his glasses to dry his feet.
               + Clearly statement B does not make sense but if you do not have an accurate model of the world you
                 will not be able to answer this question correctly. Siri, Google Now, and Watson are successful
                 artificial narrow intelligence but not artificial general intelligence because they do not have
                 accurate models of the world. [Video Source at 24:20](https://www.youtube.com/watch?v=NPtAIVieh4k)
     - The tool for understanding intelligent machines through visualizations is starting to be built at
       [WalnutiQ/toph](https://github.com/WalnutiQ/toph).

Building an AGI should be viewed as a challenge that is at a larger scale than
when John F. Kennedy proposed we go to the moon on September 12, 1962 by the
end of the decade. To build an AGI that exceeds human intelligence will
require a few more breakthroughs. Because we cannot predict when these
breakthroughs will occur the first AGI to exceed human intelligence may happen
anytime in the next 10 to 50 years.

Remember "*what I cannot create I do not understand*" was said by Richard Feynman.
Then think about what an intelligent machine needs to be able to do from first
principles.

Experiment with your model on tasks that only intelligent machines seem to be
good at. Be open to ways to change the model. Remember that the
earth was born about 4.6 billion years ago and that it took evolution about
4.6 billion - 200,000 years to create the first homo sapiens. This shit takes
time so if you're frustrated go relax.

And as stupid as it sounds remember that it is the journey you go on that makes
everything worth it when you look back. If your happiness depends on reaching
any goal in this project your happiness will only be temporary. You have made
the false assumption that your default state is not happiness because throughout
your life others have told you:

Others: `You should feel successful only if you are the best`  
But why can't it become `I should follow my curiosity to improve myself a little
bit everyday`?

The advice communicated by others whether through their actions or words comes
from a place of insecurity and fear of others while the latter comes from a place
of curiosity and love of yourself and others.

Here are some other examples of how your past experiences are pushing you
towards being less like yourself and how you can change it:

Others: `You should feel successful if you listen to what I tell you and not question it`  
Becomes: `I should be open-minded and think from 1st principles and do what I
think is right`

Others: `You should feel successful if you do the thing that will make you the most money`  
Becomes: `When I do not need the money I should do things that make me excited
and that I want to see exist in reality over doing something just for the money`

Others: `You should feel successful only if you are approved wildly`  
Becomes: `I should help others improve themselves a little bit everyday`

In each of these examples we removed happiness being dependent on some kind of success.
<b>You deserve to be happy by default</b> and should reach for your goals from
a place of curiosity and love of yourself and others instead of insecurity and fear of
others.

Sometimes life will hit you with a hammer. When it does take time to regain your hope in humanity in any way you
feel is right and take as much time as you need. When you get back up you will
be a stronger version of yourself and ready to fail even harder again.

If you are interested in becoming a researcher/developer, I would be grateful
for your collaboration as I cannot do this alone. To get started read
**[how to contribute](#how-to-contribute)** and then
**[setup development environment for Mac](#setup-development-environment-for-mac)**.

## How to contribute
1. I am looking for developers to build an open source artificial general
   intelligence. As a developer you will be given the opportunity to work on
   a variety of projects including coding a general learning algorithm and
   visualizing a general learning algorithm.

   You must meet these minimum requirements but exceptions can be made given
   applicants with exceptional backgrounds in particular areas.
   - Know how to use Git & Github.com. If you don't know how I created a 1.5
     hour playlist on how to use Git & 
     Github [here](https://www.youtube.com/watch?v=44E8o-xuxWo&list=PLPXsMt57rLtgpwFBqZq4QKxrD9Hhc_8L4).
   - Be very curious about how the human brain works and realized this is a problem you can
     and want to solve in your lifetime.
   - Written at least 2 thousand lines of Python or Javascript in your life. D3.js experience
     is a big plus!
   - Have at least 2 hours of free time each day to video chat with me 4 consecutive
     days of the week. This is based on the learning principle that if you want to
     get really good at something you have to work on it consecutively everyday.
   
3. E-mail quinnliu@vt.edu the following:
   - Link to source code of project you enjoyed working on the most preferably in
     Python or Javascript or D3.js. I am really just looking for good object oriented design 
     and good documentation.
   - Why you want to work on wAlnut. 
   - My current rule of thumb for hiring someone is if the roles where reversed and you
     were hiring me would I want to work for you. 

4. For now we are using the Git workflow model
   [here](https://github.com/WalnutiQ/wAlnut/issues/62) and the Google python style
   guide [here](https://google.github.io/styleguide/pyguide.html) to contribute to this
   repository effectively.

## Setup development environment for Mac
1. Go to the top right of this page and hit the Fork button. Then clone your forked
   repository locally.

2. Install [Python 3.5.2](https://www.python.org/) and 
   [pip](https://pip.pypa.io/en/stable/installing/). You have installed successfully
   when you can:

   ```
   $ python3 --version
   Python 3.5.2
   ```

3. Install [PyCharm Community](https://www.jetbrains.com/pycharm/download/)

4. Locally run: `pip3 install virtualenv`
   - then `cd wAlnut`
   - then `virtualenv wAlnut_environment`
   - then `source wAlnut_environment/bin/activate`
   - then `pip3 install -r requirements.txt`
   - then `nosetests -s` where -s tells nose to show print statements in test files
   - optionally type `deactivate` to leave the virtual environment

## What

## Introduction

This repo is an object oriented Python implementation of the computational framework outlined
in Dileep George's PhD thesis [*How the Brain Might Work: A Hierarchical and Temporal Model for
Learning and Recognition*](https://github.com/WalnutiQ/papers/blob/master/HowTheBrainMightWork.pdf)

After we understood the principles of flight we were able to build planes that
are much faster and can carry more weight than birds. After we understand
the human brain's computation principles we will be able to create intelligent
machines much more intelligent than humans with unimaginable capabilities. At
first it will not have any emotions like you and I. This will be a good thing
because it will force us to ask if it would be moral to create an super
intelligent machine with emotions to help us. It may not necessarily be a
good thing for the human race. For example, look at how humans treat
[intelligent pigs](http://modernfarmer.com/2014/03/pigheaded-smart-swine/).
We are more intelligent emotional machines compared to pigs and the future
super intelligent emotional machines will be much more intelligent compared to
us.

## Key Theories

1. **Theory 1:** 
   1 common learning algorithm in the neocortex of the brain
   - Supportive:
     - 1992 Paper [here](https://github.com/WalnutiQ/papers/blob/master/VisualProjectionsRouted.pdf)
       from Department of Brain and
       Cognitive Sciences at MIT.
         - Summary: A portion of wires of the optic nerve are routed to the
           auditory cortex in ferrets. The neurons of this primary auditory
           cortex(A1) with vision input did not work exactly like neurons in
           primary visual cortex(V1). Neurons in rewired A1 had larger receptive
           field sizes and other differences. However, there are also
           similarities including:
             + rewired A1 neurons showed orientation and direction selectivity.
             + similar proportions of simple, complex, and non oriented cells between
               rewired A1 and V1.
             + implies "*significant implications for possible commonalities in
               intracortical processing circuits between sensory cortices*".

     + 1988 Paper [here](https://github.com/WalnutiQ/papers/blob/master/VisualResponses.pdf)
       from Laboratoire des Neuroscience de la Vision at Universite de Paris.
         - Summary: Wires of the optic nerve were routed permanently to the
           main thalamic somatosensory nucleus in hamsters. After the hamsters
           grew up the neurons in the somatosensory cortex were recorded.
           The "*somatosensory neurons responded to visual stimulation of
           distinct receptive fields, and their response properties resembled,
           in several characteristic features, those of normal visual cortical
           neurons.*"
             + "*the same functional categories of neurons occurred in similar
               proportions, and the neurons' selectivity for the orientation or
               direction of movement of visual stimuli was comparable*" between
               normal hamsters and rewired hamsters.
             + "*These results suggest that thalamic nuclei or cortical areas at
               corresponding levels in the visual and somatosensory pathways perform
               similar transformations on their inputs*".

     + 2008 PhD thesis [here](https://github.com/WalnutiQ/papers/blob/master/HowTheBrainMightWork.pdf)
       by Dileep George from Stanford University
         - There is a idea in optimization algorithms called No Free Lunch
           theorem. No Free Lunch theorem "*state[s] that any two optimization 
           algorithms are equivalent when their performance is averaged across 
           all possible problems*".
         - "*On the surface, the NFL theorem seems to create problems for the
           idea of a common cortical algorithm. How can one mechanism/algorithm
           do very well on tasks as different as vision, audition and language?
           The answer comes from the part of the NFL theorem that talks about
           the assumptions that need to be exploited ... If the cortex is good
           at learning a wide variety of tasks using a common mechanism, then
           there must be something common about these seemingly different tasks.*".

   - Not supportive:
     + 2002 Paper [here](https://github.com/WalnutiQ/papers/blob/master/CorticalHeterogeneity.pdf)
       from Vision, Touch and Hearing Research Centre at The University of Queensland.
          - "*recent studies have revealed substantial variation in pyramidal
            cell structure in different cortical areas*".

     + 2008 PhD thesis [here](https://github.com/WalnutiQ/papers/blob/master/HowTheBrainMightWork.pdf)
       by Dileep George from Stanford University
          - "*Until we understand and explain the computational reason behind
            a large majority of such variations, the common cortical algorithm
            will have to be considered as a working hypothesis*".

   - Conclusion: If cortex X(arbitrary cortex) of the neocortex(contains visual cortex,
     auditory cortex, somatosensory cortex, and others.) can be given
     non-normal sensory input usually given to say cortex Y and then learn to
     process this new input similarly to how cortex X would process it, then 
     we can hypothesize that there is a common learning/predicting algorithm 
     in all cortices of the neocortex.

2. **Theory 2:** 
   The common learning algorithm efficiently learns about the
   world by making a specific set of assumptions about the world. This is the
   inductive bias of the common learning algorithm. 
   - Supportive:
     + Thought Experiment [here](https://www.youtube.com/watch?v=NPtAIVieh4k) by D. Scott Phoenix
       from Vicarious.com
         - "*Imagine we are designing a system to distinguish faces from non-faces and we are just
             using 32 by 32 pixel images and 256 shades of grey.*" This is a very reduced version of
             what your visual brain is doing. "*Even with that reduction it is still
             256^1024 different possible combinations you need to be able to distinguish
             between*". Because 32 by 32 with 256 different values is equivalent to the permutation
             (choose value between 0 and 256)(0-256)...32*32 more times.
         - 256^1024 is approximately 10^2400 which is a very large number. On the other hand
           a human lives about 2.5 * 10^9 seconds (80 years) and has about 10^11 neurons. And in about
           3.1 * 10^7 seconds (1 year) "*we are able to solve this problem space and distinguish 
           readily between faces and not faces. What this implies is that our brains are making
           a lot of assumptions.*"

   - Not Supportive:

   - Conclusion: We need to find all the assumptions the brain's common
     learning algorithm makes about the world by asking the question
     "*What is the basic set of assumptions that are specific enough to make
     learning feasible in a reasonable amount of time while being general
     enough to be applicable to a large class of problems?*" ~ Dileep George 

3. **Theory 2 Assumption 1:**
   A common learning algorithm must be trained on unlabeled movies of the
   world it is expected to learn to predict and classify. 
   - Supportive: 
     + A baby has to learn this way for the first year of life before it 
       understands speech.
     + 2008 PhD thesis [here](https://github.com/WalnutiQ/papers/blob/master/HowTheBrainMightWork.pdf)
       by Dileep George from Stanford University
         - Figure 1: ![alt text](https://raw.githubusercontent.com/WalnutiQ/wAlnut/master/model/images/explanatory/translation_training_creates_scale_invariance.png)
         - Notice how the scaling of "A" in (c) would be the exact same input as (b) if you translate the input
           to the left by 1 pixel.
   - Not supportive: 
   - Conclusion: Yes, specific enough to make learning feasible and general
     enough for large class of problems. 

4. **Theory 2 Assumption 2:**   
   Manifold = all the images generated by the same object in a 
   high-dimensional space. 

   If object A occurs close together to object B in time than object C then
   object A and object B are more similar. This information is used to form
   manifolds aka invariant representations of the objects.
   - Supportive: 
     + A baby has to learn this way for the first year of life before it 
       understands speech.
   - Not supportive:
   - Conclusion: Yes, specific enough to make learning feasible and general
     enough for large class of problems. 

5. **Theory 2 Assumption 3:** 
   If pattern B follows pattern A in time, then they are causally related and 
   in the future pattern A should predict pattern B.
   - Supportive:
     - The brain is constantly making predictions about the future. 
   - Not supportive:
     - How does the brain make predictions multiple time steps into the future?
   - Conclusion: Yes, specific enough to make learning feasible and general
     enough for large class of problems. 

6. **Theory 2 Assumption 4:**
   Complex invariant representations are made up of less complex invariant
   representations in a hierarchy.
   - Supportive:
     + More ideas in the universe than neurons in the brain. Also the universe
       has natural hierarchies. 
     + The neocortex has a hierarchal structure. 
   - Not supportive:
   - Conclusion: The common learning algorithm first learns invariant 
     representations of object components then learns invariant 
     representations of more complex objects in terms of the invariant 
     representations of the components.

7. **Theory 2 Assumption 5:** 
   During the current time step of the common learning algorithm, it will 
   always receive input about what muscles were used in the last time step. 
   - Supportive:
     + Consciousness doesn't get confused when the eye is moving 
       around. This means every region in the neocortex must be receiving input
       about the eye's sensori-motor movement so it can use it to accurately
       predict the future. 
  - Not supportive:
  - Conclusion: This assumption is specific enough to make learning feasible 
    in a reasonable amount of time while being general enough to be applicable 
    to a large class of problems.    

8. **Theory 2 Assumption 6:**
   Every input pattern is encoded into a sparse distributed representation of 
   the input stimuli.
   - Supportive:
     - Not possible to process every pixel in the input image.
     - Sparse distributed representations can represent vision data, audio data,
       touch data and in general any time of data can be encoded into a 
       sparse distributed representation.
   - Not supportive:
   - Conclusion: Specific enough to make learning feasible in a reasonable 
     amount of time while being general enough to be applicable to a large 
     class of problems.

## Code Outline
 
If you are confused what a file/folder is for:
```
- model/ = object oriented model implementing the key theories
  - datasets/ = contains zipped datasets generated by https://github.com/WalnutiQ/time_series_data
  - images/
    - explanatory/ = contains explanatory images about how the model looks
- tests/ = unit tests for code in model folder
- .gitignore = contains names of files/folders not to add to this repository
  but keep in your local wAlnut folder
- LICENSE.txt = GNU General Public License version 3
- README.md = the file you are reading right now
- requirements.txt = list of python library versions we are using. These are installed
                     in your virtual environment during setup 
```