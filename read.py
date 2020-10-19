

def main():
    data = []
    with open("edict.sql", "r", encoding="utf-8") as file:
        i = 0
        for line in file:
            i = i + 1
            if(i >= 100):
                lineSplit = line.split(',')
                newLine = lineSplit[1: len(lineSplit)]
                newLine[0] = newLine[0].replace(" '", "").replace("'", "")
                newLine[1] = newLine[-2].replace(" '<C><F><I><N><Q>", "").replace("</Q></N></I></F></C>')", "")
                newLine[-2] = newLine[-2].replace(" '<C><F><I><N><Q>", "").replace("</Q></N></I></F></C>')", "")
                for j in range(len(newLine)):
                    newLine[j] = newLine[j].replace("<br />-", ";").replace("<br />*",";").replace("<br /><br />", ";").replace("<br />=",";")
                #print(len(newLine))
                data.append(newLine)
                print(newLine)
                if(i == 130000): break
    
    with open('dictionary.txt', "w", encoding="utf-8") as f:
        for i in range(len(data)):
            line = data[i]
            wline = line[0] + "    " + ''.join(line[1:len(line)])
            f.write(wline)


if __name__ == '__main__':
    main()