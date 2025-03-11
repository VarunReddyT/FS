{
 "cells": [
  {
   "cell_type": "raw",
   "id": "274dd76d-9842-4b6c-aa4e-3d0758927dae",
   "metadata": {},
   "source": [
    "# Problem Statement:\n",
    "# Implement a basic diffusion model from scratch using PyTorch.\n",
    "# 1. Define the forward diffusion process (adding noise to images).\n",
    "# 2. Train a neural network (U-Net) to reverse the diffusion process.\n",
    "# 3. Generate images from pure noise using the trained model.\n",
    "\n",
    "# ANSI Diagram Representation:\n",
    "#\n",
    "# +-------------------+\n",
    "# |   Original Image  |\n",
    "# +-------------------+\n",
    "#           |\n",
    "#           v (Forward Diffusion)\n",
    "# +-------------------+\n",
    "# |  Noisy Image (t)  |\n",
    "# +-------------------+\n",
    "#           |\n",
    "#           v (Reverse Diffusion)\n",
    "# +-------------------+\n",
    "# |  Reconstructed Image |\n",
    "# +-------------------+\n",
    "#\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 59,
   "id": "9b8a6b08-8991-4fe3-b42d-e5d0b4dae8fb",
   "metadata": {},
   "outputs": [],
   "source": [
    "import torch\n",
    "import torch.nn as nn\n",
    "import torch.nn.functional as F\n",
    "import numpy as np\n",
    "import matplotlib.pyplot as plt\n",
    "from torchvision import datasets, transforms\n",
    "from torch.utils.data import DataLoader\n",
    "from tqdm import tqdm\n",
    "from PIL import Image"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "id": "5a1ebd73-1f07-4a58-9999-ebdce7526dfd",
   "metadata": {},
   "outputs": [
    {
     "data": {
      "image/png": "iVBORw0KGgoAAAANSUhEUgAAAxsAAACvCAYAAACVbcM3AAAAOXRFWHRTb2Z0d2FyZQBNYXRwbG90bGliIHZlcnNpb24zLjkuMiwgaHR0cHM6Ly9tYXRwbG90bGliLm9yZy8hTgPZAAAACXBIWXMAAA9hAAAPYQGoP6dpAABQW0lEQVR4nO2dediXY/7+z76izRaiVNOkJGWpRlKRrUWiLEkoqey7+SKMZew7JUMay2RPZQ8psiVCWbJvEZLMWJKd+/fH9+f59n5dlz7PzNfnSR3n6zgch/N57v2+7uu6757zvN7ViqIoZIwxxhhjjDG/Mf+1tA/AGGOMMcYYs3zijw1jjDHGGGNMWfDHhjHGGGOMMaYs+GPDGGOMMcYYUxb8sWGMMcYYY4wpC/7YMMYYY4wxxpQFf2wYY4wxxhhjyoI/NowxxhhjjDFlwR8bxhhjjDHGmLLgjw1jjDHGGGNMWVjuPjZeeukl9e3bV02aNFHNmjXVsGFDdevWTSNHjgzLnXPOObrzzjuXzkH+f7777jsNGzZM6667rmrVqqUOHTpo8uTJS/WYTOVZVtraI488omrVqmX/e+qpp5Lln3zySW255ZaqXbu26tevryOPPFJfffXVUjhy8wvLSlv76quvdNppp2mHHXbQGmusoWrVqukf//jHry7/6quvaocddtDKK6+sNdZYQwMHDtSCBQuS5X7++WddcMEFatq0qWrWrKlNNtlEt9xySxnPxJDlsQ3ut99+2X6xZcuWybJug0uX5bH9SdLll1+uDTfcUDVq1FDDhg315z//WYsWLUqWe+utt9S3b1/VrVtXtWvX1pZbbqmpU6eW8Ux+Y4rliGnTphUrrbRS0bx58+LMM88s/v73vxennnpq0b1796JZs2Zh2Tp16hSDBg1aOgf6/+nfv39RvXr14thjjy2uuuqqomPHjkX16tWLxx9/fKkelynNstTWpk6dWkgqjjzyyOKGG24I/y1YsCAsO2vWrKJmzZpF27ZtiyuvvLL4y1/+UtSoUaPYYYcdltLRm2Wprb377ruFpOIPf/hDsc022xSSiuuuuy677Ny5c4u11lqraNasWTFixIji7LPPLurWrVtsuummxXfffReWPeGEEwpJxQEHHFCMHj266NWrVyGpuOWWW6rgrMzy2gYHDRpU1KhRI+kX77777mRZt8Glx/La/o4//vhCUtG3b9/iyiuvLI444oiievXqRffu3cNy77//frHWWmsV66yzTnH22WcXw4cPLzbddNOievXqxaOPPloFZ/V/Z7n62Nhxxx2LevXqFZ999lnyu/nz5we9tBvk008/XUgqLrzwwoqfffPNN0WzZs2Kjh07LrXjMpVjWWprv3xsjBs3ruSyPXv2LBo0aFB88cUXFT/7+9//XkgqJk2aVM7DNL/CstTWvv3222LevHlFURTFM888s8SB9pBDDilq1apVvPfeexU/mzx5ciGpuOqqqyp+9sEHHxQrrrhicdhhh1X87Oeffy622mqrolGjRsWPP/5YnpMxFSyvbXDQoEFFnTp1Sm7TbXDpsjy2v48++qioXr16MXDgwPDzkSNHFpLCB++hhx5aVK9evXjttdcqfrZo0aKicePGRbt27cpzIr8xy5WN6u2331br1q21+uqrJ79be+21K/6/WrVqWrRokcaMGVPxZ9P99tuv4vcffvihhgwZonXWWUc1atRQ69atde2114bt/WJNGTt2rE466STVr19fderUUe/evTV37tySxzp+/HitsMIKOvDAAyt+VrNmTQ0dOlTTp0+v1DbM0mNZamuLs3DhQv3444/Z33355ZeaPHmyBgwYoFVXXbXi5/vuu69WXnll3Xbbbf/Wvsxvw7LU1mrUqKH69etX6rwmTJignXbaSX/4wx8qfta1a1e1aNEitLW77rpLP/zwgw499NBwrocccog++OADTZ8+vVL7M/85y2sb/IWffvpJX3755a/+3m1w6bI8tr/p06frxx9/VP/+/cPPf9G33nprxc8ef/xxtW3bVhtssEHFz2rXrq3evXtr5syZevPNN0vub2lTfWkfwG9JkyZNNH36dM2ePVsbbbTRry53ww03aP/999fmm29e8bLfrFkzSdL8+fO1xRZbqFq1ajr88MNVr1493X///Ro6dKi+/PJLHX300WFbZ599tqpVq6Zhw4bpk08+0fDhw9W1a1c9//zzqlWr1q8ew6xZs9SiRYvwUidJm2++uSTp+eefV+PGjf+Ty2CqgGWprf3C4MGD9dVXX2mFFVbQVlttpQsvvFCbbbZZxe9feukl/fjjj+FnkrTSSiupTZs2mjVrVmUvj/kNWRbbWik+/PBDffLJJ0lbk/6nD7zvvvsq9KxZs1SnTh1tuOGGyXK//H7LLbf8Px+T+XWWxzb4C19//bVWXXVVff3116pbt6722msvnX/++Vp55ZUrlnEbXLosj+3vu+++k6RkW7Vr15YkPffcc2HZunXrJttYfNn111///3xMZWVp/2nlt+TBBx8sVlhhhWKFFVYoOnbsWBx//PHFpEmTiu+//z5Z9tf+1DZ06NCiQYMGxaeffhp+3r9//2K11VYrvv7666Io/tea0rBhw+LLL7+sWO62224rJBUjRoxY4rG2bt262G677ZKfv/zyy4WkYtSoUZU5ZbOUWJba2rRp04rdd9+9uOaaa4q77rqrOPfcc4s111yzqFmzZjFz5syK5caNG1dIKh577LFkG3vssUdRv379Je7HlIdlqa0tzpIsBL/87vrrr09+d9xxxxWSim+//bYoiqLo1atXsd566yXLLVq0qJBUnHDCCZU+JvOfsTy2waL4nxzGsGHDirFjxxa33HJLMWjQoEJS0blz5+KHH36oWM5tcOmyPLa/5557rpBUnHnmmeHnDzzwQCGpWHnllSt+tvPOOxerr756OJ6iKIqOHTsWkoqLLrqo0se0tFiubFTdunXT9OnT1bt3b73wwgu64IIL1KNHDzVs2FB33313yfWLotCECRO08847qygKffrppxX/9ejRQ1988YVmzpwZ1tl33321yiqrVOi+ffuqQYMG4V/mcnzzzTeqUaNG8vOaNWtW/N78flmW2lqnTp00fvx4DRkyRL1799YJJ5ygp556StWqVdOJJ55Ysdwvbe7X2qXb5NJhWWprlaVUW1t8GfeVS5/lsQ1K0rnnnqvzzjtP/fr1U//+/fWPf/xDZ599tqZNm6bx48dXLOc2uHRZHttfu3bt1KFDB51//vm67rrrNGfOHN1///066KCDtOKKK4Y2dcghh+jzzz/XnnvuqVmzZumNN97Q0UcfrWeffVbSstH+lquPDUlq3769br/9dn322WeaMWOGTjzxRC1cuFB9+/bVK6+8ssR1FyxYoM8//1yjR49WvXr1wn+DBw+WJH3yySdhHf7pqlq1amrevLnmzJmzxH3VqlWr4s9oi/Ptt99W/N78vllW2lqO5s2bq0+fPpo6dap++uknSf/b5n6tXbpNLj2W5baWo1RbW3wZ95W/D5a3NvhrHHPMMfqv//ovTZkypeJnboNLn+Wx/U2YMEGbbrqphgwZoqZNm2rnnXdWv3791LZt22Dj69mzp0aOHKnHHntM7dq10wYbbKCJEyfq7LPPlqSw7O+V5SqzsTgrrbSS2rdvr/bt26tFixYaPHiwxo0bp9NOO+1X1/n5558lSQMGDNCgQYOyy2yyySa/yfE1aNBAH374YfLzefPmSZLWXXfd32Q/pvz83tvar9G4cWN9//33WrRokVZddVU1aNBA0v+2wcWZN2+e2+TvgGW1rZFSbW2NNdao+JfkBg0aaOrUqSqKQtWqVQvLSe4rq5rlpQ3+GrVq1dKaa66pf/3rXxU/cxv8/bA8tb+GDRvqiSee0JtvvqmPP/5Y66+/vurXr691111XLVq0CMsefvjhGjx4sF588cWKHOU111wjScmyv0eW24+NxfklhLj4wLZ4h/EL9erV0yqrrKKffvpJXbt2rdS2OQtAURR66623SjbcNm3aaOrUqfryyy9DSPzpp5+u+L1Z9vg9trVf45133lHNmjUr/lVko402UvXq1fXss8+qX79+Fct9//33ev7558PPzNJnWWprpGHDhqpXr16FDWBxZsyYEfq/Nm3a6Oqrr9arr76qVq1aVfzcfeXSZ1lug7/GwoUL9emnn6pevXoVP3Mb/H2yvLS/9ddfv+IvKa+88ormzZsXZtH6hTp16qhjx44VesqUKapVq5Y6d+78mx/Tb81yZaP65V8eyC8eu8WnDatTp44+//zzsNwKK6yg3XffXRMmTNDs2bOT7eQq215//fVauHBhhR4/frzmzZunnj17LvFY+/btq59++kmjR4+u+Nl3332n6667Th06dPBMVL9zlqW2ltvWCy+8oLvvvlvdu3fXf/3X/3QDq622mrp27aobb7wx7OeGG27QV199pT322GOJ+zHlYVlqa/8Ou+++u+69994wneRDDz2kN954I7S1Pn36aMUVV9QVV1xR8bOiKDRq1Cg1bNhQnTp1+s2OyeRZHtvgt99+G7b/C2eeeaaKotAOO+xQ8TO3waXL8tj+cvz88886/vjjVbt2bR188MFLXPbJJ5/U7bffrqFDh2q11VYr2zH9VlQrcndwGWWjjTbS119/rV133VUtW7bU999/ryeffFJjx45V48aNNWvWrIp5mnv16qVHH31UZ5xxhtZdd101bdpUHTp00Pz589WhQwctWLBABxxwgFq1aqV//etfmjlzpqZMmVLxp9VHHnlE2267rTbeeGNVq1ZNgwcP1vz5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",
      "text/plain": [
       "<Figure size 1000x500 with 5 Axes>"
      ]
     },
     "metadata": {},
     "output_type": "display_data"
    }
   ],
   "source": [
    "\n",
    "T = 200  \n",
    "beta_start = 1e-4\n",
    "beta_end = 0.02\n",
    "betas = torch.linspace(beta_start, beta_end, T)  \n",
    "\n",
    "alphas = 1 - betas\n",
    "alphas_cumprod = torch.cumprod(alphas, dim=0)  \n",
    "\n",
    "def add_noise(image, t):\n",
    "    \"\"\"Adds Gaussian noise to an image at time step t.\"\"\"\n",
    "    sqrt_alpha_t = torch.sqrt(alphas_cumprod[t])\n",
    "    sqrt_one_minus_alpha_t = torch.sqrt(1 - alphas_cumprod[t])\n",
    "    noise = torch.randn_like(image)\n",
    "    return sqrt_alpha_t * image + sqrt_one_minus_alpha_t * noise, noise\n",
    "\n",
    "transform = transforms.Compose([\n",
    "    transforms.Resize((32, 32)),\n",
    "    transforms.ToTensor()\n",
    "])\n",
    "\n",
    "DATASET_PATH = \"/srv/shareddata/datasets/course101/minst-data-vae\"  # Change this path to your local MNIST dataset\n",
    "\n",
    "dataset = datasets.DatasetFolder(root=DATASET_PATH, loader=lambda x: Image.open(x).convert(\"L\"), extensions=('png', 'jpg', 'jpeg'), transform=transform)\n",
    "dataloader = DataLoader(dataset, batch_size=64, shuffle=True)\n",
    "\n",
    "fig, axes = plt.subplots(1, 5, figsize=(10, 5))\n",
    "time_steps = [0, 50, 100, 150, 199]\n",
    "\n",
    "for i, t in enumerate(time_steps):\n",
    "    noisy_image, _ = add_noise(dataset[0][0], t)\n",
    "    axes[i].imshow(noisy_image.squeeze(), cmap=\"gray\")\n",
    "    axes[i].set_title(f\"Step {t}\")\n",
    "    axes[i].axis(\"off\")\n",
    "\n",
    "plt.show()\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 61,
   "id": "0e836f11-eeb0-4da8-aff2-98ee137d638d",
   "metadata": {},
   "outputs": [],
   "source": [
    "class SimpleUNet(nn.Module):\n",
    "    def __init__(self):\n",
    "        super().__init__()\n",
    "\n",
    "        self.encoder = nn.Sequential(\n",
    "            nn.Conv2d(1, 64, kernel_size=3, stride=1, padding=1),\n",
    "            nn.ReLU(),\n",
    "            nn.Conv2d(64, 128, kernel_size=3, stride=2, padding=1),\n",
    "            nn.ReLU(),\n",
    "        )\n",
    "\n",
    "        self.middle = nn.Sequential(\n",
    "            nn.Conv2d(128, 128, kernel_size=3, stride=1, padding=1),\n",
    "            nn.ReLU()\n",
    "        )\n",
    "\n",
    "        self.decoder = nn.Sequential(\n",
    "            nn.ConvTranspose2d(128, 64, kernel_size=4, stride=2, padding=1),\n",
    "            nn.ReLU(),\n",
    "            nn.Conv2d(64, 1, kernel_size=3, stride=1, padding=1),\n",
    "        )\n",
    "\n",
    "    def forward(self, x):\n",
    "        x = self.encoder(x)\n",
    "        x = self.middle(x)\n",
    "        x = self.decoder(x)\n",
    "        return x\n",
    "\n",
    "unet = SimpleUNet()\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 62,
   "id": "8b1f17b1-0d96-4bcd-a661-0201c96b04e3",
   "metadata": {},
   "outputs": [
    {
     "name": "stderr",
     "output_type": "stream",
     "text": [
      "100%|██████████| 2/2 [00:00<00:00, 34.81it/s]\n"
     ]
    },
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Epoch 1, Loss: 1.0873\n"
     ]
    },
    {
     "name": "stderr",
     "output_type": "stream",
     "text": [
      "100%|██████████| 2/2 [00:00<00:00, 39.91it/s]\n"
     ]
    },
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Epoch 2, Loss: 0.9400\n"
     ]
    },
    {
     "name": "stderr",
     "output_type": "stream",
     "text": [
      "100%|██████████| 2/2 [00:00<00:00, 40.10it/s]\n"
     ]
    },
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Epoch 3, Loss: 1.0307\n"
     ]
    },
    {
     "name": "stderr",
     "output_type": "stream",
     "text": [
      "100%|██████████| 2/2 [00:00<00:00, 40.08it/s]\n"
     ]
    },
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Epoch 4, Loss: 0.9706\n"
     ]
    },
    {
     "name": "stderr",
     "output_type": "stream",
     "text": [
      "100%|██████████| 2/2 [00:00<00:00, 39.85it/s]"
     ]
    },
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Epoch 5, Loss: 0.8263\n"
     ]
    },
    {
     "name": "stderr",
     "output_type": "stream",
     "text": [
      "\n"
     ]
    }
   ],
   "source": [
    "optimizer = torch.optim.Adam(unet.parameters(), lr=1e-3)\n",
    "loss_fn = nn.MSELoss()\n",
    "\n",
    "num_epochs = 5\n",
    "device = \"cuda\" if torch.cuda.is_available() else \"cpu\"\n",
    "unet.to(device)\n",
    "\n",
    "for epoch in range(num_epochs):\n",
    "    for images, _ in tqdm(dataloader):\n",
    "        images = images[0].to(device)  # Fix unpacking issue\n",
    "        t = torch.randint(0, T, (images.shape[0],), device=device)\n",
    "        noisy_images, noise = add_noise(images, t)\n",
    "        predicted_noise = unet(noisy_images)\n",
    "        loss = loss_fn(predicted_noise, noise)\n",
    "        optimizer.zero_grad()\n",
    "        loss.backward()\n",
    "        optimizer.step()\n",
    "    print(f\"Epoch {epoch+1}, Loss: {loss.item():.4f}\")\n",
    "\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 63,
   "id": "3f17ae2d-c78c-40f1-9a4b-297b822e5824",
   "metadata": {},
   "outputs": [
    {
     "data": {
      "image/png": "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",
      "text/plain": [
       "<Figure size 640x480 with 1 Axes>"
      ]
     },
     "metadata": {},
     "output_type": "display_data"
    }
   ],
   "source": [
    "@torch.no_grad()\n",
    "def generate_image():\n",
    "    x = torch.randn((1, 1, 32, 32), device=device)  # Start with random noise\n",
    "    for t in reversed(range(T)):\n",
    "        noise_pred = unet(x)\n",
    "        alpha_t = torch.sqrt(alphas_cumprod[t])\n",
    "        beta_t = torch.sqrt(1 - alphas_cumprod[t])\n",
    "        if t > 0:\n",
    "            z = torch.randn_like(x)\n",
    "            x = (1 / alpha_t) * (x - beta_t * noise_pred) + torch.sqrt(betas[t]) * z\n",
    "        else:\n",
    "            x = (1 / alpha_t) * (x - beta_t * noise_pred)\n",
    "    return x\n",
    "\n",
    "generated_image = generate_image().cpu().squeeze()\n",
    "plt.imshow(generated_image, cmap=\"gray\")\n",
    "plt.axis(\"off\")\n",
    "plt.show()\n"
   ]
  }
 ],
 "metadata": {
  "kernelspec": {
   "display_name": "Python 3 (ipykernel)",
   "language": "python",
   "name": "python3"
  },
  "language_info": {
   "codemirror_mode": {
    "name": "ipython",
    "version": 3
   },
   "file_extension": ".py",
   "mimetype": "text/x-python",
   "name": "python",
   "nbconvert_exporter": "python",
   "pygments_lexer": "ipython3",
   "version": "3.10.15"
  }
 },
 "nbformat": 4,
 "nbformat_minor": 5
}
