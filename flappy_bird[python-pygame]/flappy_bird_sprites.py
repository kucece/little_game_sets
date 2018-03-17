import random
import pygame

# 屏幕大小的常量
SCREEN_RECT = pygame.Rect(0, 0, 288, 512)
# 刷新的帧率
FRAME_PER_SEC = 60
# 小鸟受重力下降
BIRD_DROP_EVENT = pygame.USEREVENT
# 绿色阻挡块移动
BLOCK_MOVE_EVENT = pygame.USEREVENT + 1
# 随机位置
BLOCK_POS_EVENT = pygame.USEREVENT + 2


class GameSprite(pygame.sprite.Sprite):

    def __init__(self, image_name, speed=1):
        super().__init__()
        self.image = pygame.image.load(image_name)
        self.rect = self.image.get_rect()
        self.speed = speed

    def update_y(self):
        # 在屏幕的垂直方向上移动
        self.rect.y += self.speed

    def update_x(self):
        # 在屏幕的水平方向上移动
        self.rect.x += self.speed


class Background(GameSprite):

    def __init__(self, is_alt=False):
        super().__init__("./images/bg_day.png",speed=0)

    def update(self):
        super().update_x()


class Bird(GameSprite):

    def __init__(self):
        super().__init__("./images/bird1_2.png",speed=1.5)

    def update(self):
        super().update_y()
        if self.rect.y < self.image.get_rect().y:
            self.rect.y = self.image.get_rect()
            pass
        if self.rect.bottom > SCREEN_RECT.bottom:
            self.rect.bottom = SCREEN_RECT.bottom
            pass

class Pipe_up(GameSprite):

    def __init__(self,init_pos = SCREEN_RECT.centery):
        super().__init__("./images/pipe_up.png",speed=-1)
        self.init_pos = init_pos
        self.rect.top = init_pos + 48
        self.rect.left = SCREEN_RECT.right

    def update(self):
        super().update_x()
        if self.rect.right < 0:
            self.rect.top = self.init_pos + 48
            self.rect.left = SCREEN_RECT.right

class Pipe_down(GameSprite):

    def __init__(self,init_pos = SCREEN_RECT.centery):
        super().__init__("./images/pipe_down.png",speed=-1)
        self.init_pos = init_pos
        self.rect.bottom = init_pos - 48
        self.rect.left = SCREEN_RECT.right

    def update(self):
        super().update_x()
        if self.rect.right < 0:
            self.rect.bottom = self.init_pos - 48
            self.rect.left = SCREEN_RECT.right