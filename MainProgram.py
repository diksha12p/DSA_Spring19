from tkinter import *
from Environment import *
from Agent import *

# Default Values
default_dimension = 5
default_numberOfMines = 5

IS_MINE = True
IS_NOT_MINE = False
IS_VISITED = 1
IS_UNVISITED = 0
NUMBER_CLUES = 10


class MainProgram:
    def __init__(self):
        self.env = None
        self.agent = None
        self.dimension = default_dimension
        self.numberOfMines = default_numberOfMines

        # Tkinter Simulation Window
        self.window = Tk()
        self.window.title("MineSweeper Simulator")
        self.window.geometry('900x700+200+10')

        # Available Options
        self.controlFrame = Frame(self.window, width=200, height=700)
        self.controlFrame.pack(side=LEFT)

        self.DimensionLabel = Label(self.controlFrame, text='Dimensions')
        self.DimensionLabel.pack()

        self.DimensionEntry = Entry(self.controlFrame)
        self.DimensionEntry.pack()

        self.NumberOfMinesLabel = Label(self.controlFrame, text='Number Of Mines')
        self.NumberOfMinesLabel.pack()

        self.NumberOfMinesEntry = Entry(self.controlFrame)
        self.NumberOfMinesEntry.pack()

        self.GenerateMineFieldButton = Button(self.controlFrame, text="Generate MineField",
                                              command=self.generate_mine_field)
        self.GenerateMineFieldButton.pack()

        self.PlayMinesweeperButton = Button(self.controlFrame, text="PlayMinesweeper", command=self.play_minesweeper)
        self.PlayMinesweeperButton.pack()

        self.OverPlayMinesweeperButton = Button(self.controlFrame, text="Play Overestimated Minesweeper",
                                                command=self.play_over_es_minesweeper)
        self.OverPlayMinesweeperButton.pack()

        self.PassNumberButton = Button(self.controlFrame, text="Pass Number of Mines and Play",
                                       command=self.play_minesweeper_with_num)
        self.PassNumberButton.pack()

        self.NextStepButton = Button(self.controlFrame, text="NextStep", command=self.next_step)
        self.NextStepButton.pack()

        # Frame for drawing minefield
        self.mineFieldFrame = Frame(self.window, width=700, height=700)
        self.mineFieldFrame.pack(side=RIGHT)

        self.mineFieldCanvas = Canvas(self.mineFieldFrame, width=700, height=700)
        self.mineFieldCanvas.pack()

        self.window.mainloop()


    def generate_mine_field(self):
        try:
            self.dimension = int(self.DimensionEntry.get())
            self.numberOfMines = int(self.NumberOfMinesEntry.get())
        except ValueError:
            print("Invalid parameters")
            return None
        if self.numberOfMines < 0 or self.numberOfMines > (self.dimension * self.dimension):
            print("Invalid Entry !! Setting default value : ", default_numberOfMines)
            self.numberOfMines = default_numberOfMines
        self.env = Environment(self.dimension, self.numberOfMines)
        self.draw_mine_field(self.env.mineField)
        self.agent = Agent(self.dimension,-1)

    def draw_mine_field(self, mineField):
        tileHeight = (680) / self.dimension
        tileWidth = (680) / self.dimension
        # Clear the canvas
        self.mineFieldCanvas.delete("all")
        # for j in range(self.dimension):
        #     for i in range(self.dimension):
        #         self.mineFieldCanvas.create_rectangle(10 + i * tileWidth, 10 + j * tileHeight,
        #                                               10 + (i + 1) * tileWidth, 10 + (j + 1) * tileHeight

        tiles = [[self.mineFieldCanvas.create_rectangle(10 + i * tileWidth, 10 + j * tileHeight,
                                                        10 + (i + 1) * tileWidth, 10 + (j + 1) * tileHeight) for i in
                  range(self.dimension)] for j in range(self.dimension)]

        for i in range(self.dimension):
            for j in range(self.dimension):
                if mineField[i][j].visited == IS_UNVISITED:
                    self.mineFieldCanvas.itemconfig(tiles[i][j], fill="#c0c0c0")
                elif mineField[i][j].visited == IS_VISITED and mineField[i][j].mine == IS_MINE:
                    self.mineFieldCanvas.itemconfig(tiles[i][j], fill="#ff0000")

                elif mineField[i][j].visited == IS_VISITED and mineField[i][j].mine == IS_NOT_MINE:
                    self.mineFieldCanvas.itemconfig(tiles[i][j], fill="#ffff00")
                    self.mineFieldCanvas.create_text(
                        (10 + tileHeight / 2 + j * tileHeight, 10 + tileWidth / 2 + i * tileWidth),
                        text=mineField[i][j].clue)

    def play_minesweeper(self):
        chosen_box = self.agent.pickABox()
        while self.agent.solvedBoxes < self.dimension ** 2:
            print(self.agent.unseenBoxes, self.agent.solvedBoxes)
            print("Query: ", chosen_box.row, chosen_box.col)
            queried_box = self.env.queryBox(chosen_box)
            self.agent.updateBoxInfo(queried_box)
            chosen_box = self.agent.pickABox()
        self.draw_mine_field(self.env.mineField)

    def play_over_es_minesweeper(self):
        self.agent = MineSweeperOverestimatedAgent(self.dimension,-1)
        chosen_box = self.agent.pickABox()
        while self.agent.solvedBoxes < self.dimension ** 2:
            print(self.agent.unseenBoxes,self.agent.solvedBoxes)
            print("Query: ", chosen_box.row, chosen_box.col)
            queried_box = self.env.queryBox(chosen_box)
            self.agent.updateBoxInfo(queried_box)
            chosen_box = self.agent.pickABox()
        self.draw_mine_field(self.env.mineField)

    def play_minesweeper_with_num(self):
        self.agent = Agent(self.dimension, self.numberOfMines)
        chosen_box = self.agent.pickABox()
        while self.agent.solvedBoxes < self.dimension ** 2:
            print(self.agent.unseenBoxes, self.agent.solvedBoxes)
            print("Query: ", chosen_box.row, chosen_box.col)
            queried_box = self.env.queryBox(chosen_box)
            self.agent.updateBoxInfo(queried_box)
            chosen_box = self.agent.pickABox()
        self.draw_mine_field(self.env.mineField)

    def next_step(self):
        chosen_box = self.agent.pickABox()
        if self.agent.solvedBoxes < self.dimension ** 2:
            print("Query: ",chosen_box.row,chosen_box.col)
            queried_box = self.env.queryBox(chosen_box)
            self.agent.updateBoxInfo(queried_box)
        self.draw_mine_field(self.env.mineField)



if __name__ == "__main__":
    mp = MainProgram()


