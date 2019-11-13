import random
from sympy import symbols, S
import itertools
from sympy.logic.boolalg import is_cnf, to_cnf


# Variables
IS_MINE = True
IS_NOT_MINE = False
NUMBER_CLUES = 10


class Box:
    def __init__(self, row, col, prob):
        self.row = row
        self.col = col
        self.prob = prob
        self.mine = IS_NOT_MINE
        self.clue = NUMBER_CLUES
        self.denotation = symbols("A" + str(row) + str(col))
        self.mineFlag = IS_NOT_MINE
        self.solved = False

    def __str__(self):
        return "Box(" + str(self.row) + "," + str(self.col) + ")"


class Agent:
    def __init__(self, dimension, number):
        self.dimension = dimension
        if number == -1:
            self.agentObservedMineField = [[Box(i, j, 0.5) for j in range(self.dimension)] for i in random(self.dimension)]
        else:
            probability = float(number / (self.dimension * self.dimension))
            self.agentObservedMineField = [[Box(i, j, probability) for j in range(self.dimension)] for i in
                                           range(self.dimension)]
            self.unseenBoxes = self.dimension * self.dimension
            self.solvedBoxes = 0
            self.knowledgeBase = S.true
            self.fringe = []
            self.numberOfMines = number

    def getNeighbours(self, box):
        neighbours = []
        row, col = box.row, box.col

        for x, y in [[(row + i, col + j) for j in (-1, 0, 1)] for i in (-1, 0, 1)]:
            if (0 <= x < dim) and (0 <= y < dim):
                if self.agentObservedMineField[x][y].mineFlag == False:
                    neighbours.append(self.agentObservedMineField[x][y])

            return neighbours

    def pickABox(self):
        # If the fringe isn't empty, choose a box from the fringe else, a random unsolved Box
        if len(self.fringe) != 0:
            self.fringe.sort(key=lambda x: x.prob)
            for chosen_node in self.fringe:
                if chosen_node.prob <= 0.2:
                    return self.agentObservedMineField[chosen_node.row][chosen_node.col]

        # Choose a random unsolved box
        row = random.randrange(self.dimension)
        col = random.randrange(self.dimension)
        if self.agentObservedMineField[row][col].solved == True and self.solvedBoxes < self.dimension * 2:
            while self.agentObservedMineField[row][col].solved == True:
                row = random.randrange(self.dimension)
                col = random.randrange(self.dimension)
        return self.agentObservedMineField[row][col]


    def updateBoxInfo(self, box):
        row = box.row
        col = box.col
        self.unseenBoxes -= 1
        self.solvedBoxes += 1
        if self.agentObservedMineField[row][col] in self.fringe:
            self.fringe.remove(agentObservedMineField[row][col])

        self.agentObservedMineField[row][col].mine = box.mine
        self.agentObservedMineField[row][col].solved = True
        self.agentObservedMineField[row][col].clue = box.clue
        neighbours = self.getNeighbours(box)

        for neighbour in neighbours:
            if neighbour.solved == False and neighbour not in self.fringe:
                self.fringe.append(neighbour)

        self.addInfoToKnowledgeBase(agentObservedMineField[row][col])


    def addInfoToKnowledgeBase(self, box):
        expression_box_neighbours = S.false
        neighbours = self.getNeighbours(box)

        # CASE 1: The clue is 0 i.e. all neighbours are 'SAFE'
        if box.clue == 0:
            for neighbour in neighbours:
                if neighbour in self.fringe:
                    # As 'Pass by Value' is being used here
                    self.fringe.remove(neighbour)
                self.knowledgeBase = self.knowledgeBase.subs({neighbour.denotation: False})
                if neighbour.solved == False:
                    self.observedMineField[neighbour.row][neighbour.col].prob = 0.0
                    self.fringe.append(neighbour)

        # CASE 2: 100 percent confidence in all the neighbours being a mine
        elif box.clue == 8:
            for neighbour in neighbours:
                if neighbour in self.fringe:
                    self.fringe.remove(neighbour)
                neighbour.solved = True
                neighbour.mineFlag = IS_MINE
                self.solvedBoxes += 1
                self.knowledgeBase = self.knowledgeBase.subs({neighbour.denotation : True})
                self.agentObservedMineField[neighbour.row][neighbour.col].prob = 1.0


        # CASE 3: The clue lies between 0 and 1 (exclusive)
        else:
            for subset in itertools.combinations(neighbours, box.clue):
                expression_subset = S.true

                # Forming the expression for the neighbours of a given box
                for neighbour in neighbours:
                    if neighbour not in subset:
                        expression_subset = expression_subset & ~neighbour.denotation
                    else:
                        expression_subset = expression_subset & neighbour.denotation
                expression_box_neighbours = expression_box_neighbours | expression_subset

            # Updating the expression obtained above
            for neighbour in neighbours:
                if neighbour.solved == True:
                    if neighbour.mineFlag:
                        expression_box_neighbours = expression_box_neighbours.subs({neighbour.denotation : True})
                    else:
                        expression_box_neighbours = expression_box_neighbours.subs({neighbour.denotation: False})

            expression = expression_box_neighbours

            if not is_cnf(expression):
                expression = to_cnf(expression, simplify = True)

            self.updateKnowledgeBaseInfo(expression, box)

    def updateKnowledgeBaseInfo(self, expression, box):
        var = box.symbol
        if box.mine == True:
            self.knowledgeBase = self.knowledgeBase.subs({var : True})
        else:
            self.knowledgeBase = self.knowledgeBase.subs({var: False})

        # Update the Knowledge Base
        self.knowledgeBase = self.knowledgeBase & expression

        if (self.knowledgeBase != S.true):
            # Keeping 'all_models' as True to obtain a generator of models
            generator_models = satisfiable(self.knowledgeBase, all_models=True)

            if len(self.fringe) != 0:
                counts = {}
                sumMinesHere = 0
                amount = 0

                # Calculate the probability
                for model in generator_models:
                    if not (isinstance(model, bool)):
                        try:
                            for var, value in model.items():
                                if value:
                                    sumMinesHere += 1
                                    if var in counts.keys():
                                        counts[var] = counts[var] + 1
                                    else:
                                        counts[var] = 1
                            amount = amount + 1

                        except Exception as e:
                            print("Exception At", e)
                            template = "An exception of type {0} occurred. Arguments:\n{1!r}"
                            message = template.format(type(e).__name__, e.args)
                            print(message)

                # Calculate the fringe probabilities
                for var, value in counts.items():
                    var = str(var)
                    row, col = int(var[0]), int(var[1])
                    if amount != 0:
                        if ( float(value) / amount) >= 1:
                            print("Mine found for var : ", var)
                            # self.agentObservedMineField[row][col].mineFlag = IS_MINE
                            self.agentObservedMineField[row][col].solved = True
                            if self.agentObservedMineField[row][col] in self.fringe:
                                self.fringe.remove(self.agentObservedMineField[row][col])
                            self.unseenBoxes -= 1
                            self.solvedBoxes += 1
                            self.agentObservedMineField[row][col].mineFlag = IS_MINE
                            var = self.agentObservedMineField[row][col].denotation
                            self.knowledgeBase = self.knowledgeBase.subs({var : True})
                            self.knowledgeBase = self.knowledgeBase & S.true
                            self.agentObservedMineField[row][col].prob = 1.0
                        else:
                            if self.agentObservedMineField[row][col] in self.fringe:
                                self.agentObservedMineField[row][col].prob += float(value) / amount
                            else:
                                self.agentObservedMineField[row][col].prob += float(value) / amount

                if amount != 0:
                    prob = float((self.numberOfMines - self.solvedBoxes) * amount - sumMinesHere) / (
                            amount * self.unseenBoxes)
                    for row_here in range(self.dimension):
                        for col_here in range(self.dimension):

                            if self.agentObservedMineField[row_here][col_here].solved == False \
                                    and self.agentObservedMineField[row_here][col_here] not in self.fringe:
                                self.agentObservedMineField[row_here][col_here].prob = prob


class OverestimatedAgent(Agent):
    def __init__(self, dimension, number):
        super(OverestimatedAgent, self).__init__(dimension, number)

    def addInfoToKnowledgeBase(self, box):
        expression_box_neighbours = S.false
        neighbours = self.getNeighbours(box)
        if box.clue == 0:
            for neighbour in neighbours:
                if neighbour in self.fringe:
                    self.fringe.remove(neighbour)
                self.knowledgeBase = self.knowledgeBase.subs({neighbour.denotation : False})
                if not neighbour.solved:
                    self.agentObservedMineField[neighbour.row][neighbour.col].prob = 0.0
                    self.fringe.append(neighbour)
        elif box.clue == 8:
            for neighbour in neighbours:
                if neighbour in self.fringe:
                    self.fringe.remove(neighbour)
                neighbour.solved = True
                neighbour.mineFlag = IS_MINE
                self.solvedBoxes += 1
                self.knowledgeBase = self.knowledgeBase.subs({neighbour.symbol : True})
                self.agentObservedMineField[neighbour.row][neighbour.col].prob = 1.0
        else:
            for clue_i in range(0, box.clue +1):
                expression_subset = S.true
                for neighbour in neighbours:
                    if neighbour not in subset:
                        expression_subset = expression_subset & ~neighbour.symbol
                    else:
                        expression_subset = expression_subset & neighbour.symbol
                expression_box_neighbours = expression_box_neighbours | (expression_subset)
                for neighbour in neighbours:
                    if neighbour.solved:
                        if neighbour.mineFlag:
                            expression_box_neighbours = expression_box_neighbours.subs({neighbour.symbol: True})
                        else:
                            expression_box_neighbours = expression_box_neighbours.subs({neighbour.symbol: False})

                # print("Expression Generated:", expression_box_neighbours)
                expression = expression_box_neighbours

                if not is_cnf(expression):
                    expression = to_cnf(expression, simplify=True)

                self.updateKnowledgeBaseInfo(expression, box)






















